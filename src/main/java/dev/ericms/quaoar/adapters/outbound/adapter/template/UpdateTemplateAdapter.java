package dev.ericms.quaoar.adapters.outbound.adapter.template;

import dev.ericms.quaoar.application.core.domain.Template;
import dev.ericms.quaoar.application.ports.outbound.template.UpdateTemplateOutboundPort;
import org.springframework.stereotype.Component;

@Component
public class UpdateTemplateAdapter implements UpdateTemplateOutboundPort {

    @Override
    public void update(Template template) {

    }
}
