package dev.ericms.quaoar.adapters.outbound.adapter.template;

import dev.ericms.quaoar.adapters.outbound.mapper.TemplateOutboundMapper;
import dev.ericms.quaoar.adapters.outbound.repository.TemplateRepository;
import dev.ericms.quaoar.adapters.outbound.repository.entity.TemplateEntity;
import dev.ericms.quaoar.application.core.domain.Template;
import dev.ericms.quaoar.application.core.dto.PageResponseDTO;
import dev.ericms.quaoar.application.core.enums.SortDirection;
import dev.ericms.quaoar.application.ports.outbound.template.FindAllTemplateOutboundPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class FindAllTemplateAdapter implements FindAllTemplateOutboundPort {

    @Autowired
    private TemplateRepository templateRepository;

    @Autowired
    private TemplateOutboundMapper templateOutboundMapper;

    @Override
    @Transactional(readOnly = true)
    public PageResponseDTO<Template> findAll(SortDirection sort, int page, int size) {
        Sort.Direction direction = (sort != null) ? Sort.Direction.valueOf(sort.name()) : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, "title"));

        Page<TemplateEntity> result = templateRepository.findAll(pageable);

        return templateOutboundMapper.toPage(result);
    }
}
