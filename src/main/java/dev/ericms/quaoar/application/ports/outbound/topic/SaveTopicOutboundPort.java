package dev.ericms.quaoar.application.ports.outbound.topic;

import dev.ericms.quaoar.application.core.domain.Topic;

public interface SaveTopicOutboundPort {

    Topic save(Topic topic);

}
