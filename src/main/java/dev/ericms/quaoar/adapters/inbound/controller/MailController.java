package dev.ericms.quaoar.adapters.inbound.controller;

import dev.ericms.quaoar.adapters.inbound.controller.dto.request.SendMailRequest;
import dev.ericms.quaoar.adapters.inbound.controller.utils.BaseResponse;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static dev.ericms.quaoar.adapters.inbound.controller.utils.BaseResponse.*;
import static dev.ericms.quaoar.adapters.inbound.controller.utils.Constants.MAIL_SENT_WITH_SUCCESS;

@RestController
@RequestMapping("/v1/mail")
public class MailController {

    @PostMapping(
            value = "/send",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<BaseResponse> send(@Valid @RequestBody SendMailRequest requestPayload) {


        return createResponse(HttpStatus.OK, MAIL_SENT_WITH_SUCCESS.getMessage());
    }

}
