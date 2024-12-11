package dev.ericms.quaoar.adapters.outbound.adapter.template;

import dev.ericms.quaoar.adapters.inbound.controller.mapper.TemplateMapper;
import dev.ericms.quaoar.adapters.outbound.repository.TemplateRepository;
import dev.ericms.quaoar.adapters.outbound.repository.entity.TemplateEntity;
import dev.ericms.quaoar.application.core.domain.Template;
import dev.ericms.quaoar.application.ports.outbound.template.SaveTemplateOutboundPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class SaveTemplateAdapter implements SaveTemplateOutboundPort {

    @Autowired
    private TemplateRepository templateRepository;

    @Autowired
    private TemplateMapper templateMapper;

    @Override
    @Transactional
    public void save(Template template) {
        TemplateEntity templateEntity = templateMapper.toEntity(template);

        templateRepository.save(templateEntity);
    }
}
