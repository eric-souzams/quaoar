package dev.ericms.quaoar.application.core.usecase.template;

import dev.ericms.quaoar.application.core.domain.Template;
import dev.ericms.quaoar.application.ports.inbound.template.FindTemplateByIdInboundPort;
import dev.ericms.quaoar.application.ports.inbound.template.UpdateTemplateInboundPort;
import dev.ericms.quaoar.application.ports.outbound.template.UpdateTemplateOutboundPort;

import java.util.UUID;

public class UpdateTemplateUseCase implements UpdateTemplateInboundPort {

    private final UpdateTemplateOutboundPort updateTemplateOutboundPort;

    private final FindTemplateByIdInboundPort findTemplateByIdInboundPort;

    public UpdateTemplateUseCase(UpdateTemplateOutboundPort updateTemplateOutboundPort,
                                 FindTemplateByIdInboundPort findTemplateByIdInboundPort) {
        this.updateTemplateOutboundPort = updateTemplateOutboundPort;
        this.findTemplateByIdInboundPort = findTemplateByIdInboundPort;
    }

    @Override
    public void update(Template template, UUID templateId) {
        Template result = findTemplateByIdInboundPort.find(templateId);

        updateData(template, result);

        updateTemplateOutboundPort.update(result);
    }

    private void updateData(Template payloadRequest, Template target) {
        target.setActive(payloadRequest.getActive());
        target.setTitle(payloadRequest.getTitle());
        target.setContent(payloadRequest.getContent());
    }
}
