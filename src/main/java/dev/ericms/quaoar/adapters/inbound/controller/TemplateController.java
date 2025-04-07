package dev.ericms.quaoar.adapters.inbound.controller;

import dev.ericms.quaoar.adapters.inbound.controller.dto.request.CreateTemplateRequestDto;
import dev.ericms.quaoar.adapters.inbound.controller.dto.request.UpdateTemplateRequestDto;
import dev.ericms.quaoar.adapters.inbound.controller.mapper.TemplateInboundMapper;
import dev.ericms.quaoar.application.core.dto.PageResponseDTO;
import dev.ericms.quaoar.application.core.enums.SortDirection;
import dev.ericms.quaoar.application.core.domain.Template;
import dev.ericms.quaoar.application.ports.inbound.template.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
    private TemplateInboundMapper templateInboundMapper;

    @Autowired
    private SaveTemplateInboundPort saveTemplateInboundPort;

    @Autowired
    private UpdateTemplateInboundPort updateTemplateInboundPort;

    @Autowired
    private DeleteTemplateInboundPort deleteTemplateInboundPort;

    @Autowired
    private FindTemplateByIdInboundPort findTemplateByIdInboundPort;

    @Autowired
    private FindAllTemplateInboundPort findAllTemplateInboundPort;

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<Object> create(@Valid @RequestBody CreateTemplateRequestDto payload) {
        saveTemplateInboundPort.save(templateInboundMapper.toDomain(payload));

        return createdResponse(TEMPLATE_CREATED_WITH_SUCCESS.getMessage());
    }

    @GetMapping(
            value = "/{templateId}",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<Object> find(@PathVariable("templateId") UUID templateId) {
        Template template = findTemplateByIdInboundPort.find(templateId);

        return okResponse(templateInboundMapper.toResponseDto(template));
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

    @PutMapping(
            value = "/{templateId}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<Object> update(@Valid @RequestBody UpdateTemplateRequestDto payload,
                                         @PathVariable("templateId") UUID templateId) {
        updateTemplateInboundPort.update(templateInboundMapper.toDomain(payload), templateId);

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
