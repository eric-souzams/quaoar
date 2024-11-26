package dev.ericms.quaoar.infrastructure.events.strategies;

import dev.ericms.quaoar.application.core.events.UnsubscribeToTopicDomainEvent;
import dev.ericms.quaoar.application.core.events.interfaces.DomainEvent;
import dev.ericms.quaoar.infrastructure.events.event.UnsubscribeToTopicEvent;
import dev.ericms.quaoar.infrastructure.events.interfaces.EventPublisherStrategy;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class UnsubscribeToTopicEventPublisherStrategy implements EventPublisherStrategy<UnsubscribeToTopicDomainEvent> {

    private final ApplicationEventPublisher publisher;

    public UnsubscribeToTopicEventPublisherStrategy(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public void publish(DomainEvent event) {
        if (event instanceof UnsubscribeToTopicDomainEvent) {
            UnsubscribeToTopicEvent unsubscribeToTopicEvent = new UnsubscribeToTopicEvent(this, (UnsubscribeToTopicDomainEvent) event);
            publisher.publishEvent(unsubscribeToTopicEvent);
        }
    }
}
