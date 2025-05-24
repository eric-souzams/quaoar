package dev.ericms.quaoar.application.ports.inbound.message;

import dev.ericms.quaoar.application.core.domain.Message;

public interface DeleteMessageByIdInboundPort {

    void delete(Message message);

}
