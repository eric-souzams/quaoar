package dev.ericms.quaoar.adapters.outbound.adapter.template;

import dev.ericms.quaoar.application.core.domain.Template;
import dev.ericms.quaoar.application.ports.outbound.template.SaveTemplateOutboundPort;
import org.springframework.stereotype.Component;

@Component
public class SaveTemplateAdapter implements SaveTemplateOutboundPort {

    @Override
    public Template save(Template template) {
        return null;
    }
}
