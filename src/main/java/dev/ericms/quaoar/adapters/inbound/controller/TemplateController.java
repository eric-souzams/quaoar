package dev.ericms.quaoar.adapters.inbound.controller;

import dev.ericms.quaoar.adapters.inbound.controller.dto.request.CreateTemplateRequestDto;
import dev.ericms.quaoar.adapters.inbound.controller.dto.request.UpdateTemplateRequestDto;
import dev.ericms.quaoar.adapters.inbound.controller.mapper.TemplateMapper;
import dev.ericms.quaoar.application.core.domain.Template;
import dev.ericms.quaoar.application.ports.inbound.template.DeleteTemplateInboundPort;
import dev.ericms.quaoar.application.ports.inbound.template.FindTemplateByIdInboundPort;
import dev.ericms.quaoar.application.ports.inbound.template.SaveTemplateInboundPort;
import dev.ericms.quaoar.application.ports.inbound.template.UpdateTemplateInboundPort;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static dev.ericms.quaoar.application.core.utils.Constants.*;
import static dev.ericms.quaoar.infrastructure.utils.BaseResponse.*;

@RestController
@RequestMapping("/v1/templates")
public class TemplateController {

    @Autowired
    private TemplateMapper templateMapper;

    @Autowired
    private SaveTemplateInboundPort saveTemplateInboundPort;

    @Autowired
    private UpdateTemplateInboundPort updateTemplateInboundPort;

    @Autowired
    private DeleteTemplateInboundPort deleteTemplateInboundPort;

    @Autowired
    private FindTemplateByIdInboundPort findTemplateByIdInboundPort;

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<Object> create(@Valid @RequestBody CreateTemplateRequestDto payload) {
        saveTemplateInboundPort.save(templateMapper.toDomain(payload));

        return createdResponse(TEMPLATE_CREATED_WITH_SUCCESS.getMessage());
    }

    @GetMapping(
            value = "/{templateId}",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<Object> find(@PathVariable("templateId") UUID templateId) {
        Template template = findTemplateByIdInboundPort.find(templateId);

        return okResponse(templateMapper.toResponseDto(template));
    }

    @GetMapping(
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<Page<Object>> findAll(Pageable pageable) {
        return null;
    }

    @PutMapping(
            value = "/{templateId}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<Object> update(@Valid @RequestBody UpdateTemplateRequestDto payload,
                                         @PathVariable("templateId") UUID templateId) {
        updateTemplateInboundPort.update(templateMapper.toDomain(payload), templateId);

        return okResponse(TEMPLATE_UPDATED_WITH_SUCCESS.getMessage());
    }

    @DeleteMapping(
            value = "/{templateId}",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<Object> delete(@PathVariable("templateId") UUID templateId) {
        deleteTemplateInboundPort.delete(templateId);

        return deletedResponse(TEMPLATE_DELETED_WITH_SUCCESS.getMessage());
    }
}
