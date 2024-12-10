package dev.ericms.quaoar.application.ports.outbound.template;

import dev.ericms.quaoar.application.core.domain.Template;

public interface SaveTemplateOutboundPort {

    Template save(Template template);

}
