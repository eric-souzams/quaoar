package dev.ericms.quaoar.application.core.usecase.template;

import dev.ericms.quaoar.application.core.domain.Template;
import dev.ericms.quaoar.application.core.exception.BusinessException;
import dev.ericms.quaoar.application.ports.inbound.template.FindTemplateByNameInboundPort;
import dev.ericms.quaoar.application.ports.outbound.template.FindTemplateByNameOutboundPort;

import static dev.ericms.quaoar.application.core.utils.Constants.TEMPLATE_NOT_FOUND;

public class FindTemplateByNameUseCase implements FindTemplateByNameInboundPort {

    private final FindTemplateByNameOutboundPort findTemplateByNameOutboundPort;

    public FindTemplateByNameUseCase(FindTemplateByNameOutboundPort findTemplateByNameOutboundPort) {
        this.findTemplateByNameOutboundPort = findTemplateByNameOutboundPort;
    }

    @Override
    public Template find(String name) {
        return findTemplateByNameOutboundPort.find(name)
                .orElseThrow(() -> new BusinessException(TEMPLATE_NOT_FOUND.getMessage()));
    }
}
