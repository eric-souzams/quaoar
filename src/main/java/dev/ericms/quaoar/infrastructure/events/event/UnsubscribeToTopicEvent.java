package dev.ericms.quaoar.infrastructure.events.event;

import dev.ericms.quaoar.application.core.events.UnsubscribeToTopicDomainEvent;
import org.springframework.context.ApplicationEvent;

public class UnsubscribeToTopicEvent extends ApplicationEvent {

    private final UnsubscribeToTopicDomainEvent unsubscribeToTopicEvent;

    public UnsubscribeToTopicEvent(Object source, UnsubscribeToTopicDomainEvent unsubscribeToTopicEvent) {
        super(source);
        this.unsubscribeToTopicEvent = unsubscribeToTopicEvent;
    }

    public UnsubscribeToTopicDomainEvent getBody() {
        return unsubscribeToTopicEvent;
    }
}
