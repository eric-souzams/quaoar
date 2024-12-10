package dev.ericms.quaoar.application.core.usecase.template;

import dev.ericms.quaoar.application.ports.inbound.template.DeleteTemplateInboundPort;
import dev.ericms.quaoar.application.ports.outbound.template.DeleteTemplateOutboundPort;

import java.util.UUID;

public class DeleteTemplateUseCase implements DeleteTemplateInboundPort {

    private final DeleteTemplateOutboundPort deleteTemplateOutboundPort;

    public DeleteTemplateUseCase(DeleteTemplateOutboundPort deleteTemplateOutboundPort) {
        this.deleteTemplateOutboundPort = deleteTemplateOutboundPort;
    }

    @Override
    public void delete(UUID templateId) {
        deleteTemplateOutboundPort.delete(templateId);
    }
}
