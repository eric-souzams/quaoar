package dev.ericms.quaoar.adapters.outbound.adapter.template;

import dev.ericms.quaoar.adapters.outbound.mapper.TemplateOutboundMapper;
import dev.ericms.quaoar.adapters.outbound.repository.TemplateRepository;
import dev.ericms.quaoar.adapters.outbound.repository.entity.TemplateEntity;
import dev.ericms.quaoar.application.core.domain.Template;
import dev.ericms.quaoar.application.ports.outbound.template.DeleteTemplateOutboundPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DeleteTemplateAdapter implements DeleteTemplateOutboundPort {

    @Autowired
    private TemplateRepository templateRepository;

    @Autowired
    private TemplateOutboundMapper templateOutboundMapper;

    @Override
    @Transactional
    public void delete(Template template) {
        TemplateEntity templateEntity = templateOutboundMapper.toEntity(template);

        templateRepository.delete(templateEntity);
    }
}
