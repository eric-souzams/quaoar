package dev.ericms.quaoar.infrastructure.events.strategies;

import dev.ericms.quaoar.application.core.events.DeleteUserDomainEvent;
import dev.ericms.quaoar.application.core.events.interfaces.DomainEvent;
import dev.ericms.quaoar.infrastructure.events.event.DeleteUserEvent;
import dev.ericms.quaoar.infrastructure.events.interfaces.EventPublisherStrategy;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class DeleteUserEventPublisherStrategy implements EventPublisherStrategy<DeleteUserDomainEvent> {

    private final ApplicationEventPublisher publisher;

    public DeleteUserEventPublisherStrategy(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public void publish(DomainEvent event) {
        if (event instanceof DeleteUserDomainEvent) {
            DeleteUserEvent deleteUserDomainEvent = new DeleteUserEvent(this, (DeleteUserDomainEvent) event);
            publisher.publishEvent(deleteUserDomainEvent);
        }
    }
}
