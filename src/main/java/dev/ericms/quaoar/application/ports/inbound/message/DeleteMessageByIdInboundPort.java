package dev.ericms.quaoar.application.ports.inbound.message;

import java.util.UUID;

public interface DeleteMessageByIdInboundPort {

    void delete(UUID messageId);

}
