package dev.ericms.quaoar.application.ports.outbound.topic;

import dev.ericms.quaoar.application.core.domain.Topic;

import java.util.List;
import java.util.UUID;

public interface FindTopicsByMessageIdOutboundPort {

    List<Topic> find(UUID messageId);

}
