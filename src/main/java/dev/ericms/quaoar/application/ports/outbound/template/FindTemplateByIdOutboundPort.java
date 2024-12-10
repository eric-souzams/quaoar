package dev.ericms.quaoar.application.ports.outbound.template;

import dev.ericms.quaoar.application.core.domain.Template;

import java.util.Optional;
import java.util.UUID;

public interface FindTemplateByIdOutboundPort {

    Optional<Template> find(UUID templateId);

}
