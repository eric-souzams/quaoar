package dev.ericms.quaoar.application.ports.outbound.events;

import dev.ericms.quaoar.application.core.events.interfaces.DomainEvent;

public interface EventPublisherOutboundPort {

    void publishEvent(DomainEvent event);

}
