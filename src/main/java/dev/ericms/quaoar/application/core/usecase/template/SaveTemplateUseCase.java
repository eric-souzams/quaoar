package dev.ericms.quaoar.application.core.usecase.template;

import dev.ericms.quaoar.application.core.domain.Template;
import dev.ericms.quaoar.application.ports.inbound.template.SaveTemplateInboundPort;
import dev.ericms.quaoar.application.ports.outbound.template.SaveTemplateOutboundPort;

public class SaveTemplateUseCase implements SaveTemplateInboundPort {

    private final SaveTemplateOutboundPort saveTemplateOutboundPort;

    public SaveTemplateUseCase(SaveTemplateOutboundPort saveTemplateOutboundPort) {
        this.saveTemplateOutboundPort = saveTemplateOutboundPort;
    }

    @Override
    public Template save(Template template) {
        return saveTemplateOutboundPort.save(template);
    }
}
