package dev.ericms.quaoar.application.ports.outbound.template;

import dev.ericms.quaoar.application.core.domain.Template;

public interface DeleteTemplateOutboundPort {

    void delete(Template template);

}
