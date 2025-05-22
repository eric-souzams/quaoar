package dev.ericms.quaoar.application.ports.inbound.template;

import dev.ericms.quaoar.application.core.domain.Template;

public interface FindTemplateByNameInboundPort {

    Template find(String name);

}
