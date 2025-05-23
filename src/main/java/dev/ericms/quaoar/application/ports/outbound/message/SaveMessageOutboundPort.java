package dev.ericms.quaoar.application.ports.outbound.message;

import dev.ericms.quaoar.application.core.domain.Message;

public interface SaveMessageOutboundPort {

    void save(Message message);

}
