package dev.ericms.quaoar.adapters.outbound.adapter.template;

import dev.ericms.quaoar.adapters.outbound.mapper.TemplateOutboundMapper;
import dev.ericms.quaoar.adapters.outbound.repository.TemplateRepository;
import dev.ericms.quaoar.adapters.outbound.repository.entity.TemplateEntity;
import dev.ericms.quaoar.application.core.domain.Template;
import dev.ericms.quaoar.application.ports.outbound.template.FindTemplateByNameOutboundPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class FindTemplateByNameAdapter implements FindTemplateByNameOutboundPort {

    @Autowired
    private TemplateRepository templateRepository;

    @Autowired
    private TemplateOutboundMapper templateOutboundMapper;

    @Override
    @Transactional(readOnly = true)
    public Optional<Template> find(String name) {
        Optional<TemplateEntity> templateEntity = templateRepository.findByName(name);

        return templateEntity.map(template -> templateOutboundMapper.toDomain(template));
    }
}
