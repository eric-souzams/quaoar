package dev.ericms.quaoar.application.ports.outbound.message;

import dev.ericms.quaoar.application.core.domain.Message;

public interface DeleteMessageOutboundPort {

    void delete(Message message);

}
