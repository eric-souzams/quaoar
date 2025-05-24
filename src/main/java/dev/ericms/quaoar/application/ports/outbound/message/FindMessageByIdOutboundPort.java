package dev.ericms.quaoar.application.ports.outbound.message;

import dev.ericms.quaoar.application.core.domain.Message;

import java.util.Optional;
import java.util.UUID;

public interface FindMessageByIdOutboundPort {

    Optional<Message> find(UUID messageId);

}
