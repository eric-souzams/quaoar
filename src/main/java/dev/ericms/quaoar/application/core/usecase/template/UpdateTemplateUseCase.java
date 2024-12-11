package dev.ericms.quaoar.application.core.usecase.template;

import dev.ericms.quaoar.application.core.domain.Template;
import dev.ericms.quaoar.application.ports.inbound.template.FindTemplateByIdInboundPort;
import dev.ericms.quaoar.application.ports.inbound.template.SaveTemplateInboundPort;
import dev.ericms.quaoar.application.ports.inbound.template.UpdateTemplateInboundPort;

import java.util.UUID;

public class UpdateTemplateUseCase implements UpdateTemplateInboundPort {

    private final SaveTemplateInboundPort saveTemplateInboundPort;

    private final FindTemplateByIdInboundPort findTemplateByIdInboundPort;

    public UpdateTemplateUseCase(SaveTemplateInboundPort saveTemplateInboundPort, FindTemplateByIdInboundPort findTemplateByIdInboundPort) {
        this.saveTemplateInboundPort = saveTemplateInboundPort;
        this.findTemplateByIdInboundPort = findTemplateByIdInboundPort;
    }

    @Override
    public void update(Template template, UUID templateId) {
        Template result = findTemplateByIdInboundPort.find(templateId);

        updateData(template, result);

        saveTemplateInboundPort.save(result);
    }

    private void updateData(Template payloadRequest, Template target) {
        target.setActive(payloadRequest.getActive());
        target.setTitle(payloadRequest.getTitle());
        target.setContent(payloadRequest.getContent());
    }
}
