package dev.ericms.quaoar.adapters.outbound.client;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.AmazonSimpleEmailServiceException;
import com.amazonaws.services.simpleemail.model.RawMessage;
import com.amazonaws.services.simpleemail.model.SendRawEmailRequest;
import com.amazonaws.services.simpleemail.model.SendRawEmailResult;
import dev.ericms.quaoar.adapters.inbound.controller.dto.request.SendMailRequestDto;
import dev.ericms.quaoar.adapters.outbound.repository.enums.MessageStatus;
import dev.ericms.quaoar.application.core.domain.Contact;
import dev.ericms.quaoar.application.core.domain.Message;
import dev.ericms.quaoar.application.core.domain.Template;
import dev.ericms.quaoar.application.core.domain.Topic;
import dev.ericms.quaoar.application.core.exception.BusinessException;
import dev.ericms.quaoar.application.ports.inbound.message.SaveMessageInboundPort;
import dev.ericms.quaoar.application.ports.inbound.template.FindTemplateByNameInboundPort;
import dev.ericms.quaoar.application.ports.inbound.topic.CheckIfExistsTopicInboundPort;
import dev.ericms.quaoar.application.ports.inbound.topic.FindTopicByNameInboundPort;
import dev.ericms.quaoar.application.ports.outbound.client.MailClientOutboundPort;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.internet.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.*;

import static dev.ericms.quaoar.application.core.utils.Constants.TEMPLATE_OR_PARAMS_INVALID;
import static dev.ericms.quaoar.infrastructure.utils.Validation.*;

@Component
public class MailClient implements MailClientOutboundPort {

     private static final Logger logger = LoggerFactory.getLogger(MailClient.class);

     @Autowired
     private AmazonSimpleEmailService mailClient;

     @Autowired
     private CheckIfExistsTopicInboundPort checkIfExistsTopicInboundPort;

     @Autowired
     private FindTopicByNameInboundPort findTopicByNameInboundPort;

     @Autowired
     private FindTemplateByNameInboundPort findTemplateByNameInboundPort;

     @Autowired
     private SaveMessageInboundPort saveMessageInboundPort;

     @Value("${messaging.aws.ses.from}")
     private String senderMailAddress;

     @Override
     public void send(SendMailRequestDto sendMailRequestDto) {
          try {
               if (!isEmpty(sendMailRequestDto.getTopics()))
                    sendMailRequestDto.getRecipientsTo().addAll(getRecipientsFromTopics(sendMailRequestDto.getTopics()));

               Template template = null;
               if (isNotBlank(sendMailRequestDto.getTemplate())) {
                    template = findTemplateByNameInboundPort.find(sendMailRequestDto.getTemplate());

                    String mailBody = processTemplate(template.getContent(), sendMailRequestDto.getTemplateParams());
                    sendMailRequestDto.setContent(mailBody);
               }

               MimeMessage mail = buildMailMessage(sendMailRequestDto);
               SendRawEmailRequest rawEmailRequest = convertToRawMessageRequest(mail);

               SendRawEmailResult result = null;

               try {
                    result = mailClient.sendRawEmail(rawEmailRequest);
               } catch (AmazonSimpleEmailServiceException exception) {
                    logger.error("MC - Error sending raw email: {}", exception.getMessage());
               }

               saveMessageLog(sendMailRequestDto, result, senderMailAddress, template);
          } catch (MessagingException | IOException exception) {
               logger.error("MC - Error sending email: {}", exception.getMessage());
          }
     }

     private List<String> getRecipientsFromTopics(List<String> topics) {
          Set<String> recipients = new HashSet<>();

          for (String topic : topics) {
               if (checkIfExistsTopicInboundPort.check(topic)) {
                    Topic result = findTopicByNameInboundPort.find(topic);

                    for (Contact addressMail : result.getContacts()) {
                         if (isNotBlank(addressMail.getEmail())) {
                              recipients.add(addressMail.getEmail());
                         }
                    }
               }
          }

          return recipients.stream().toList();
     }

     private MimeMessage buildMailMessage(SendMailRequestDto sendMailRequestDto) throws MessagingException, IOException {
          Session session = Session.getDefaultInstance(new Properties());

          MimeMessage mail = new MimeMessage(session);
          mail.setSubject(sendMailRequestDto.getSubject(), "UTF-8");
          mail.setFrom(senderMailAddress);

          if (isNotBlank(sendMailRequestDto.getReplyTo())) mail.setReplyTo(getReplyTo(sendMailRequestDto));

          if (!sendMailRequestDto.getRecipientsTo().isEmpty()) {
               mail.setRecipients(jakarta.mail.Message.RecipientType.TO,
                       InternetAddress.parse(String.join(",", sendMailRequestDto.getRecipientsTo())));
          }

          if (!sendMailRequestDto.getRecipientsCc().isEmpty()) {
               mail.setRecipients(jakarta.mail.Message.RecipientType.CC,
                       InternetAddress.parse(String.join(",", sendMailRequestDto.getRecipientsCc())));
          }

          if (!sendMailRequestDto.getRecipientsBcc().isEmpty()) {
               mail.setRecipients(jakarta.mail.Message.RecipientType.BCC,
                       InternetAddress.parse(String.join(",", sendMailRequestDto.getRecipientsBcc())));
          }

          MimeMultipart completeMessage = getMimeMultipart(sendMailRequestDto);

          mail.setContent(completeMessage);

          return mail;
     }

     private static MimeMultipart getMimeMultipart(SendMailRequestDto sendMailRequestDto) throws MessagingException, IOException {
          MimeMultipart completeMessage = new MimeMultipart("mixed"); // Complete message
          MimeMultipart messageBody = new MimeMultipart("alternative"); // Message body

          MimeBodyPart wrapperPart = new MimeBodyPart(); // Wrapper part
          MimeBodyPart htmlPart = new MimeBodyPart(); // HTML part

          htmlPart.setContent(sendMailRequestDto.getContent(), "text/html; charset=UTF-8");
          messageBody.addBodyPart(htmlPart);
          wrapperPart.setContent(messageBody);
          completeMessage.addBodyPart(wrapperPart);

          if (!isEmpty(sendMailRequestDto.getAttachments())) {
               for (MultipartFile file : sendMailRequestDto.getAttachments()) {
                    if (!file.isEmpty()) {
                         MimeBodyPart attachmentPart = new MimeBodyPart();
                         attachmentPart.setFileName(MimeUtility.encodeText(isNotBlank(file.getOriginalFilename())
                                 ? file.getOriginalFilename() : UUID.randomUUID().toString(), "UTF-8", null));
                         attachmentPart.setContent(file.getBytes(), file.getContentType());
                         completeMessage.addBodyPart(attachmentPart);
                    }
               }
          }

          return completeMessage;
     }

     private static InternetAddress[] getReplyTo(SendMailRequestDto sendMailRequestDto) throws AddressException {
          return new InternetAddress[]{ new InternetAddress(sendMailRequestDto.getReplyTo()) };
     }

     private String processTemplate(String template, Map<String, String> templateParams) {
          if (template == null || templateParams == null) {
               throw new BusinessException(TEMPLATE_OR_PARAMS_INVALID.getMessage());
          }

          for (Map.Entry<String, String> entry : templateParams.entrySet()) {
               String placeholder = "{{" + entry.getKey() + "}}";
               template = template.replace(placeholder, entry.getValue());
          }

          return template;
     }

     private SendRawEmailRequest convertToRawMessageRequest(MimeMessage mail) {
          try {
               ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
               mail.writeTo(outputStream);
               RawMessage rawMessage = new RawMessage(ByteBuffer.wrap(outputStream.toByteArray()));
               return new SendRawEmailRequest(rawMessage);
          } catch (IOException | MessagingException exception) {
               logger.error("MC - Failed to convert to rawRequest: {}", exception.getMessage());
          }
          return null;
     }

     @Transactional
     private void saveMessageLog(SendMailRequestDto sendMailRequestDto, SendRawEmailResult result, String senderMailAddress, Template template) {
          Message message = sendMailRequestDto.convertToMessage();

          message.setEmailFrom(senderMailAddress);
          message.setTemplate(template);
          message.setMessageId((result != null && isNotBlank(result.getMessageId())) ? result.getMessageId() : null);
          message.setStatus((result != null && isNotBlank(result.getMessageId())) ? MessageStatus.DELIVER : MessageStatus.FAILURE);

          saveMessageInboundPort.save(message);
     }

}
