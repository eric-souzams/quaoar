package dev.ericms.quaoar.application.ports.outbound;

import dev.ericms.quaoar.application.core.events.interfaces.DomainEvent;

public interface EventPublisherOutboundPort {

    void publishEvent(DomainEvent event);

}
