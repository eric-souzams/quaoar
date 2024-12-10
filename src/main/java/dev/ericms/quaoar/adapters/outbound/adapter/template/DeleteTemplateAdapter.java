package dev.ericms.quaoar.adapters.outbound.adapter.template;

import dev.ericms.quaoar.application.ports.outbound.template.DeleteTemplateOutboundPort;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DeleteTemplateAdapter implements DeleteTemplateOutboundPort {

    @Override
    public void delete(UUID templateId) {

    }
}
