package dev.ericms.quaoar.infrastructure.events.event;

import dev.ericms.quaoar.application.core.events.SubscribeToTopicDomainEvent;
import org.springframework.context.ApplicationEvent;

public class SubscribeToTopicEvent extends ApplicationEvent {

    private final SubscribeToTopicDomainEvent subscribeToTopicDomainEvent;

    public SubscribeToTopicEvent(Object source, SubscribeToTopicDomainEvent subscribeToTopicDomainEvent) {
        super(source);
        this.subscribeToTopicDomainEvent = subscribeToTopicDomainEvent;
    }

    public SubscribeToTopicDomainEvent getBody() {
        return subscribeToTopicDomainEvent;
    }
}
