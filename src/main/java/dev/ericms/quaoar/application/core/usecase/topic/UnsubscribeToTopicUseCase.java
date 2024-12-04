package dev.ericms.quaoar.application.core.usecase.topic;

import dev.ericms.quaoar.application.ports.inbound.topic.UnsubscribeToTopicInbound;
import dev.ericms.quaoar.application.ports.outbound.topic.UnsubscribeToTopicOutbound;

public class UnsubscribeToTopicUseCase implements UnsubscribeToTopicInbound {

   private final UnsubscribeToTopicOutbound unsubscribeToTopicOutbound;

    public UnsubscribeToTopicUseCase(UnsubscribeToTopicOutbound unsubscribeToTopicOutbound) {
        this.unsubscribeToTopicOutbound = unsubscribeToTopicOutbound;
    }

    @Override
    public void unsubscribe(String topic, String email) {
        unsubscribeToTopicOutbound.unsubscribe(topic, email);
    }
}
