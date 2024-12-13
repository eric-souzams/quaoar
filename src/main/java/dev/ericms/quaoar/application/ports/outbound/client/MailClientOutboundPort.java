package dev.ericms.quaoar.application.ports.outbound.client;

import dev.ericms.quaoar.adapters.inbound.controller.dto.request.SendMailRequestDto;
import jakarta.mail.MessagingException;

public interface MailClientOutboundPort {

     void send(SendMailRequestDto sendMailRequestDto) throws MessagingException;

}
