package dev.ericms.quaoar.application.ports.inbound.topic;

import dev.ericms.quaoar.application.core.domain.Topic;

public interface FindTopicByNameInboundPort {

    Topic find(String topic);

}
