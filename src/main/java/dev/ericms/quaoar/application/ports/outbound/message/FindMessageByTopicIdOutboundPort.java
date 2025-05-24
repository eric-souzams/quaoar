package dev.ericms.quaoar.application.ports.outbound.message;

import dev.ericms.quaoar.application.core.domain.Message;

import java.util.List;
import java.util.UUID;

public interface FindMessageByTopicIdOutboundPort {

    List<Message> find(UUID topicId);

}
