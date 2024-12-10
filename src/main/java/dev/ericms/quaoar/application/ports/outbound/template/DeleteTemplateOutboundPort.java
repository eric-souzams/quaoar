package dev.ericms.quaoar.application.ports.outbound.template;

import java.util.UUID;

public interface DeleteTemplateOutboundPort {

    void delete(UUID templateId);

}
