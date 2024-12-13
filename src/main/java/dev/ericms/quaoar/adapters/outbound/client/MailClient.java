package dev.ericms.quaoar.adapters.outbound.client;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import dev.ericms.quaoar.adapters.inbound.controller.dto.request.SendMailRequestDto;
import dev.ericms.quaoar.application.core.domain.Contact;
import dev.ericms.quaoar.application.core.domain.Topic;
import dev.ericms.quaoar.application.ports.inbound.topic.CheckIfExistsTopicInboundPort;
import dev.ericms.quaoar.application.ports.inbound.topic.FindTopicByNameInboundPort;
import dev.ericms.quaoar.application.ports.outbound.client.MailClientOutboundPort;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.internet.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import static dev.ericms.quaoar.infrastructure.utils.Validation.*;

@Component
public class MailClient implements MailClientOutboundPort {

     @Autowired
     private AmazonSimpleEmailService mailClient;

     @Autowired
     private CheckIfExistsTopicInboundPort checkIfExistsTopicInboundPort;

     @Autowired
     private FindTopicByNameInboundPort findTopicByNameInboundPort;

     @Value("${messaging.aws.ses.from}")
     private String sender;

     @Override
     public void send(SendMailRequestDto sendMailRequestDto) throws MessagingException {
          MimeMessage mail = buildMailMessage(sendMailRequestDto);

          if (!isEmpty(sendMailRequestDto.getTopics()))
               sendMailRequestDto.getRecipients().addAll(getRecipientsFromTopics(sendMailRequestDto.getTopics()));

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

     private MimeMessage buildMailMessage(SendMailRequestDto sendMailRequestDto) throws MessagingException {
          Session session = Session.getDefaultInstance(new Properties());

          MimeMessage mail = new MimeMessage(session);
          mail.setSubject(sendMailRequestDto.getSubject(), "UTF-8");
          mail.setFrom(sender);
          mail.setReplyTo(getReplyTo(sendMailRequestDto));

          MimeMultipart completeMessage = getMimeMultipart(sendMailRequestDto);

          mail.setContent(completeMessage);

          return mail;
     }

     private static MimeMultipart getMimeMultipart(SendMailRequestDto sendMailRequestDto) throws MessagingException {
          MimeMultipart completeMessage = new MimeMultipart(); // Complete message
          MimeMultipart messageBody = new MimeMultipart("alternative"); // Message body

          MimeBodyPart wrapperPart = new MimeBodyPart(); // Wrapper part
          MimeBodyPart htmlPart = new MimeBodyPart(); // HTML part

          htmlPart.setContent(sendMailRequestDto.getContent(), "text/html; charset=UTF-8");
          messageBody.addBodyPart(htmlPart);
          wrapperPart.setContent(messageBody);
          completeMessage.addBodyPart(wrapperPart);
          return completeMessage;
     }

     private static InternetAddress[] getReplyTo(SendMailRequestDto sendMailRequestDto) throws AddressException {
          return new InternetAddress[]{new InternetAddress(sendMailRequestDto.getReplyTo())};
     }

}
