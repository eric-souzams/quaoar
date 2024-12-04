package dev.ericms.quaoar.application.ports.outbound.topic;

import dev.ericms.quaoar.application.core.domain.Topic;

import java.util.Optional;

public interface FindTopicByNameOutboundPort {

    Optional<Topic> find(String topic);

}
