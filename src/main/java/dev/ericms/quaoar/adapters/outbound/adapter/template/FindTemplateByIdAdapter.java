package dev.ericms.quaoar.adapters.outbound.adapter.template;

import dev.ericms.quaoar.application.core.domain.Template;
import dev.ericms.quaoar.application.ports.outbound.template.FindTemplateByIdOutboundPort;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class FindTemplateByIdAdapter implements FindTemplateByIdOutboundPort {

    @Override
    public Optional<Template> find(UUID templateId) {
        return Optional.empty();
    }
}
