package dev.ericms.quaoar.application.ports.outbound.message;

import java.util.UUID;

public interface DisassociateMessageToTopicOutboundPort {

    void disassociate(UUID messageId, UUID topicId);

}
