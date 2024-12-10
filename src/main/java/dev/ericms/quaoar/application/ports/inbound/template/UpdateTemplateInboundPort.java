package dev.ericms.quaoar.application.ports.inbound.template;

import dev.ericms.quaoar.application.core.domain.Template;

import java.util.UUID;

public interface UpdateTemplateInboundPort {

    void update(Template template, UUID templateId);

}
