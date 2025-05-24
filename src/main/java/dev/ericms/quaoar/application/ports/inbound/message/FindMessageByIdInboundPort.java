package dev.ericms.quaoar.application.ports.inbound.message;

import dev.ericms.quaoar.application.core.domain.Message;

import java.util.UUID;

public interface FindMessageByIdInboundPort {

    Message find(UUID messageId);

}
