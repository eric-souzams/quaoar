package dev.ericms.quaoar.application.ports.inbound.message;

import dev.ericms.quaoar.application.core.domain.Message;

public interface SaveMessageInboundPort {

    void save(Message message);

}
