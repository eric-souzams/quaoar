package dev.ericms.quaoar.application.ports.inbound.topic;

import dev.ericms.quaoar.application.core.domain.Topic;

public interface SaveTopicInboundPort {

    Topic save(Topic topic);

}
