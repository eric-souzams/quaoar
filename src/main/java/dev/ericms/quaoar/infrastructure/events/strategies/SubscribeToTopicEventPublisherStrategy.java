package dev.ericms.quaoar.infrastructure.events.strategies;

import dev.ericms.quaoar.application.core.events.SubscribeToTopicDomainEvent;
import dev.ericms.quaoar.application.core.events.interfaces.DomainEvent;
import dev.ericms.quaoar.infrastructure.events.event.SubscribeToTopicEvent;
import dev.ericms.quaoar.infrastructure.events.interfaces.EventPublisherStrategy;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class SubscribeToTopicEventPublisherStrategy implements EventPublisherStrategy<SubscribeToTopicDomainEvent> {

    private final ApplicationEventPublisher publisher;

    public SubscribeToTopicEventPublisherStrategy(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public void publish(DomainEvent event) {
        if (event instanceof SubscribeToTopicDomainEvent) {
            SubscribeToTopicEvent subscribeToTopicEvent = new SubscribeToTopicEvent(this, (SubscribeToTopicDomainEvent) event);
            publisher.publishEvent(subscribeToTopicEvent);
        }
    }
}
