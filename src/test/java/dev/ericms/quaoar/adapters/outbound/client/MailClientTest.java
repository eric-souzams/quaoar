package dev.ericms.quaoar.adapters.outbound.client;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.AmazonSimpleEmailServiceException;
import com.amazonaws.services.simpleemail.model.SendRawEmailResult;
import dev.ericms.quaoar.adapters.inbound.controller.dto.request.SendMailRequestDto;
import dev.ericms.quaoar.adapters.outbound.repository.enums.MessageStatus;
import dev.ericms.quaoar.application.core.domain.AbstractTopic;
import dev.ericms.quaoar.application.core.domain.Contact;
import dev.ericms.quaoar.application.core.domain.Template;
import dev.ericms.quaoar.application.core.domain.Topic;
import dev.ericms.quaoar.application.core.exception.BusinessException;
import dev.ericms.quaoar.application.ports.inbound.message.SaveMessageInboundPort;
import dev.ericms.quaoar.application.ports.inbound.template.FindTemplateByNameInboundPort;
import dev.ericms.quaoar.application.ports.inbound.topic.CheckIfExistsTopicInboundPort;
import dev.ericms.quaoar.application.ports.inbound.topic.FindTopicByNameInboundPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Profile;
import org.springframework.mock.web.MockMultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

@Profile("test")
@ExtendWith(MockitoExtension.class)
class MailClientTest {

    @InjectMocks
    private MailClient mailClient;

    @Mock
    private AmazonSimpleEmailService mailClientAws;

    @Mock
    private CheckIfExistsTopicInboundPort checkIfExistsTopicInboundPort;

    @Mock
    private FindTopicByNameInboundPort findTopicByNameInboundPort;

    @Mock
    private FindTemplateByNameInboundPort findTemplateByNameInboundPort;

    @Mock
    private SaveMessageInboundPort saveMessageInboundPort;

    private static final String MESSAGE_UUID = "682e5597-c898-8010-adb2-559300753f0d";

    private SendMailRequestDto requestMock() {
        SendMailRequestDto dto = new SendMailRequestDto();
        dto.setSubject("Subject Test");
        dto.setContent("Hello <b>{{name}}</b>");
        dto.setRecipientsTo(List.of("destiny@test.com"));
        return dto;
    }

    @Test
    void shouldSendEmailWithTemplate() {
        SendMailRequestDto dto = requestMock();
        dto.setTemplate("welcome");
        dto.setTemplateParams(Map.of("name", "João"));

        Template template = new Template();
        template.setContent("Hello <b>{{name}}</b>");

        when(findTemplateByNameInboundPort.find("welcome")).thenReturn(template);
        when(mailClientAws.sendRawEmail(any())).thenReturn(new SendRawEmailResult().withMessageId(MESSAGE_UUID));

        mailClient.send(dto);

        verify(mailClientAws).sendRawEmail(any());
        verify(findTemplateByNameInboundPort, times(1)).find(anyString());
        verify(saveMessageInboundPort).save(argThat(m ->
                MESSAGE_UUID.equals(m.getMessageId()) && m.getStatus() == MessageStatus.DELIVER
        ));
    }

    @Test
    void shouldThrowExceptionWhenForgotToSendTemplateParams() {
        SendMailRequestDto dto = requestMock();
        dto.setTemplate("welcome");

        Template template = new Template();
        template.setContent("Hello <b>{{name}}</b>");

        when(findTemplateByNameInboundPort.find("welcome")).thenReturn(template);

        assertThrows(BusinessException.class, () -> mailClient.send(dto));

        verify(mailClientAws, never()).sendRawEmail(any());
        verify(findTemplateByNameInboundPort, times(1)).find(anyString());
        verify(saveMessageInboundPort, never()).save(any());
    }

    @Test
    void shouldThrowExceptionWhenNotFoundTemplate() {
        SendMailRequestDto dto = requestMock();
        dto.setTemplate("welcome");

        when(findTemplateByNameInboundPort.find("welcome")).thenReturn(null);

        assertThrows(BusinessException.class, () -> mailClient.send(dto));

        verify(mailClientAws, never()).sendRawEmail(any());
        verify(findTemplateByNameInboundPort, times(1)).find(anyString());
        verify(saveMessageInboundPort, never()).save(any());
    }

    @Test
    void shouldSendEmailWithAttachments() {
        SendMailRequestDto dto = requestMock();
        dto.setTemplate("welcome");
        dto.setTemplateParams(Map.of("name", "João"));

        MockMultipartFile file = new MockMultipartFile(
                "file", "file.txt", "text/plain", "content".getBytes()
        );
        dto.setAttachments(List.of(file));

        Template template = new Template();
        template.setContent("Hello <b>{{name}}</b>");

        when(findTemplateByNameInboundPort.find("welcome")).thenReturn(template);
        when(mailClientAws.sendRawEmail(any())).thenReturn(new SendRawEmailResult().withMessageId(MESSAGE_UUID));

        mailClient.send(dto);

        verify(mailClientAws).sendRawEmail(any());
        verify(saveMessageInboundPort).save(argThat(m ->
                MESSAGE_UUID.equals(m.getMessageId()) && m.getStatus() == MessageStatus.DELIVER
        ));
    }

    @Test
    void shouldSendEmailWithoutTemplate() {
        SendMailRequestDto dto = requestMock();
        dto.setContent("<p>Some test</p>");

        when(mailClientAws.sendRawEmail(any())).thenReturn(new SendRawEmailResult().withMessageId(MESSAGE_UUID));

        mailClient.send(dto);

        verify(mailClientAws).sendRawEmail(any());
        verify(saveMessageInboundPort).save(argThat(m ->
                MESSAGE_UUID.equals(m.getMessageId()) && m.getStatus() == MessageStatus.DELIVER
        ));
    }

    @Test
    void shouldSaveErrorLogWhenCannotSendAnEmail() {
        SendMailRequestDto dto = requestMock();

        when(mailClientAws.sendRawEmail(any())).thenReturn(new SendRawEmailResult().withMessageId(null));

        mailClient.send(dto);

        verify(mailClientAws).sendRawEmail(any());
        verify(saveMessageInboundPort).save(argThat(m ->
                (m.getMessageId() == null) && m.getStatus() == MessageStatus.FAILURE
        ));
    }

    @Test
    void shouldSendEmailWithTopicContacts() {
        SendMailRequestDto dto = requestMock();
        dto.setTopics(List.of("marketing"));
        dto.setRecipientsTo(new ArrayList<>());

        Contact contact = new Contact(UUID.fromString(MESSAGE_UUID), "Joao",
                "test@test.com", false, false, "", LocalDateTime.now(),
                LocalDateTime.now(), List.of(new AbstractTopic("test")));

        Topic topic = new Topic();
        topic.setContacts(List.of(contact));

        when(checkIfExistsTopicInboundPort.check("marketing")).thenReturn(true);
        when(findTopicByNameInboundPort.find("marketing")).thenReturn(topic);
        when(mailClientAws.sendRawEmail(any())).thenReturn(new SendRawEmailResult().withMessageId(MESSAGE_UUID));

        mailClient.send(dto);

        verify(mailClientAws).sendRawEmail(any());
        verify(saveMessageInboundPort).save(argThat(m ->
                MESSAGE_UUID.equals(m.getMessageId()) && m.getStatus() == MessageStatus.DELIVER
        ));
    }

    @Test
    void shouldThrowBusinessExceptionWhenTrySendAnEmail() {
        SendMailRequestDto dto = requestMock();

        when(mailClientAws.sendRawEmail(any())).thenThrow(new BusinessException("MC-1 - Error sending email"));

        assertThrows(BusinessException.class, () -> mailClient.send(dto));

        verify(saveMessageInboundPort, never()).save(any());
    }

    @Test
    void shouldThrowAmazonSimpleEmailServiceExceptionWhenTrySendAnEmail() {
        SendMailRequestDto dto = requestMock();

        when(mailClientAws.sendRawEmail(any())).thenThrow(new AmazonSimpleEmailServiceException("Unavailable Service"));

        assertThrows(BusinessException.class, () -> mailClient.send(dto));

        verify(saveMessageInboundPort, never()).save(any());
    }

}