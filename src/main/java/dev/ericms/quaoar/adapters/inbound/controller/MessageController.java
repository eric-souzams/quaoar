package dev.ericms.quaoar.adapters.inbound.controller;

import dev.ericms.quaoar.adapters.inbound.controller.mapper.MessageInboundMapper;
import dev.ericms.quaoar.application.core.domain.Message;
import dev.ericms.quaoar.application.core.domain.Template;
import dev.ericms.quaoar.application.core.dto.PageResponseDTO;
import dev.ericms.quaoar.application.core.enums.SortDirection;
import dev.ericms.quaoar.application.ports.inbound.message.FindMessageByIdInboundPort;
import dev.ericms.quaoar.application.ports.inbound.template.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static dev.ericms.quaoar.application.core.utils.Constants.*;
import static dev.ericms.quaoar.infrastructure.utils.BaseResponse.*;

@RestController
@RequestMapping("/v1/messages")
public class MessageController {

    @Autowired
    private DeleteTemplateInboundPort deleteTemplateInboundPort;

    @Autowired
    private FindMessageByIdInboundPort findMessageByIdInboundPort;

    @Autowired
    private FindAllTemplateInboundPort findAllTemplateInboundPort;

    @Autowired
    private MessageInboundMapper messageInboundMapper;

    @GetMapping(
            value = "/{messageId}",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<Object> find(@PathVariable("messageId") UUID messageId) {
        Message message = findMessageByIdInboundPort.find(messageId);

        return okResponse(messageInboundMapper.toResponseDto(message));
    }

    @GetMapping(
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<Object> findAll(
            @RequestParam(required = false) SortDirection sort,
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int size
    ) {
        PageResponseDTO<Template> templates = findAllTemplateInboundPort.findAll(sort, page, size);

        return okResponseBasic(templates);
    }

    @DeleteMapping(
            value = "/{messageId}",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<Object> delete(@PathVariable("messageId") UUID messageId) {
        deleteTemplateInboundPort.delete(messageId);

        return deletedResponse(TEMPLATE_DELETED_WITH_SUCCESS.getMessage());
    }
}
