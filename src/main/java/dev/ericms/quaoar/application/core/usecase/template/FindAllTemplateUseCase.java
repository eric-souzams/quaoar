package dev.ericms.quaoar.application.core.usecase.template;

import dev.ericms.quaoar.application.core.domain.Template;
import dev.ericms.quaoar.application.core.dto.PageResponseDTO;
import dev.ericms.quaoar.application.core.enums.SortDirection;
import dev.ericms.quaoar.application.ports.inbound.template.FindAllTemplateInboundPort;
import dev.ericms.quaoar.application.ports.outbound.template.FindAllTemplateOutboundPort;

public class FindAllTemplateUseCase implements FindAllTemplateInboundPort {

    private final FindAllTemplateOutboundPort findAllTemplateOutboundPort;

    public FindAllTemplateUseCase(FindAllTemplateOutboundPort findAllTemplateOutboundPort) {
        this.findAllTemplateOutboundPort = findAllTemplateOutboundPort;
    }

    @Override
    public PageResponseDTO<Template> findAll(SortDirection sort, int page, int size) {
        return findAllTemplateOutboundPort.findAll(sort, page, size);
    }
}
