package dev.ericms.quaoar.application.core.usecase.topic;

import dev.ericms.quaoar.application.ports.inbound.topic.SubscribeToTopicInbound;
import dev.ericms.quaoar.application.ports.outbound.topic.SubscribeToTopicOutbound;

public class SubscribeToTopicUseCase implements SubscribeToTopicInbound {

    private final SubscribeToTopicOutbound subscribeToTopicOutbound;

    public SubscribeToTopicUseCase(SubscribeToTopicOutbound subscribeToTopicOutbound) {
        this.subscribeToTopicOutbound = subscribeToTopicOutbound;
    }

    @Override
    public void subscribe(String topic, String email) {
        subscribeToTopicOutbound.subscribe(topic, email);
    }
}
