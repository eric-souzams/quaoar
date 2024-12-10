package dev.ericms.quaoar.application.ports.inbound.template;

import dev.ericms.quaoar.application.core.domain.Template;

import java.util.UUID;

public interface FindTemplateByIdInboundPort {

    Template find(UUID templateId);

}
