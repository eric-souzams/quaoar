package dev.ericms.quaoar.application.ports.inbound.template;

import java.util.UUID;

public interface DeleteTemplateInboundPort {

    void delete(UUID templateId);

}
