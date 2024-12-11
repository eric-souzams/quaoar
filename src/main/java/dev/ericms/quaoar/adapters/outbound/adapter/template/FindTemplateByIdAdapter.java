package dev.ericms.quaoar.adapters.outbound.adapter.template;

import dev.ericms.quaoar.adapters.inbound.controller.mapper.TemplateMapper;
import dev.ericms.quaoar.adapters.outbound.repository.TemplateRepository;
import dev.ericms.quaoar.adapters.outbound.repository.entity.TemplateEntity;
import dev.ericms.quaoar.application.core.domain.Template;
import dev.ericms.quaoar.application.ports.outbound.template.FindTemplateByIdOutboundPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Component
public class FindTemplateByIdAdapter implements FindTemplateByIdOutboundPort {

    @Autowired
    private TemplateRepository templateRepository;

    @Autowired
    private TemplateMapper templateMapper;

    @Override
    @Transactional(readOnly = true)
    public Optional<Template> find(UUID templateId) {
        Optional<TemplateEntity> templateEntity = templateRepository.findById(templateId);

        return templateEntity.map(template -> templateMapper.toDomain(template));
    }
}
