package dev.ericms.quaoar.application.core.usecase.template;

import dev.ericms.quaoar.application.core.domain.Template;
import dev.ericms.quaoar.application.core.exception.BusinessException;
import dev.ericms.quaoar.application.ports.inbound.template.FindTemplateByIdInboundPort;
import dev.ericms.quaoar.application.ports.outbound.template.FindTemplateByIdOutboundPort;

import java.util.UUID;

import static dev.ericms.quaoar.application.core.utils.Constants.TEMPLATE_NOT_FOUND;

public class FindTemplateByIdUseCase implements FindTemplateByIdInboundPort {

    private final FindTemplateByIdOutboundPort findTemplateByIdOutboundPort;

    public FindTemplateByIdUseCase(FindTemplateByIdOutboundPort findTemplateByIdOutboundPort) {
        this.findTemplateByIdOutboundPort = findTemplateByIdOutboundPort;
    }

    @Override
    public Template find(UUID templateId) {
        return findTemplateByIdOutboundPort.find(templateId)
                .orElseThrow(() -> new BusinessException(TEMPLATE_NOT_FOUND.getMessage()));
    }
}
