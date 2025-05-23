package dev.ericms.quaoar.application.ports.outbound.message;

import java.util.List;
import java.util.UUID;

public interface AssociateMessageToTopicOutboundPort {

    void associate(UUID messageId, List<String> topics);

}
