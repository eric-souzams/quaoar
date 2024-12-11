package dev.ericms.quaoar.application.core.usecase.template;

import dev.ericms.quaoar.application.core.domain.Template;
import dev.ericms.quaoar.application.ports.inbound.template.DeleteTemplateInboundPort;
import dev.ericms.quaoar.application.ports.inbound.template.FindTemplateByIdInboundPort;
import dev.ericms.quaoar.application.ports.outbound.template.DeleteTemplateOutboundPort;

import java.util.UUID;

public class DeleteTemplateUseCase implements DeleteTemplateInboundPort {

    private final DeleteTemplateOutboundPort deleteTemplateOutboundPort;

    private final FindTemplateByIdInboundPort findTemplateByIdInboundPort;

    public DeleteTemplateUseCase(DeleteTemplateOutboundPort deleteTemplateOutboundPort, FindTemplateByIdInboundPort findTemplateByIdInboundPort) {
        this.deleteTemplateOutboundPort = deleteTemplateOutboundPort;
        this.findTemplateByIdInboundPort = findTemplateByIdInboundPort;
    }

    @Override
    public void delete(UUID templateId) {
        Template template = findTemplateByIdInboundPort.find(templateId);

        deleteTemplateOutboundPort.delete(template);
    }
}
