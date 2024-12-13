package dev.ericms.quaoar.adapters.inbound.controller;

import dev.ericms.quaoar.adapters.inbound.controller.dto.request.SendMailRequestDto;

import dev.ericms.quaoar.application.ports.outbound.client.MailClientOutboundPort;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static dev.ericms.quaoar.infrastructure.utils.BaseResponse.*;
import static dev.ericms.quaoar.application.core.utils.Constants.MAIL_SENT_WITH_SUCCESS;

@RestController
@RequestMapping("/v1/mail")
public class MailController {

    @Autowired
    private MailClientOutboundPort mailClient;

    @PostMapping(
            value = "/send",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<Object> send(@Valid @RequestBody SendMailRequestDto payload) throws MessagingException {
        mailClient.send(payload);

        return createResponse(HttpStatus.OK, MAIL_SENT_WITH_SUCCESS.getMessage());
    }

}
