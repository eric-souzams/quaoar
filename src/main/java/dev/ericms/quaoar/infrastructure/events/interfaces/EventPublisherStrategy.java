package dev.ericms.quaoar.infrastructure.events.interfaces;

import dev.ericms.quaoar.application.core.event.interfaces.DomainEvent;

public interface EventPublisherStrategy<T extends DomainEvent> {

    void publish(DomainEvent event);

}
