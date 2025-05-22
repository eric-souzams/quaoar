package dev.ericms.quaoar.application.ports.outbound.template;

import dev.ericms.quaoar.application.core.domain.Template;

import java.util.Optional;

public interface FindTemplateByNameOutboundPort {

    Optional<Template> find(String name);

}
