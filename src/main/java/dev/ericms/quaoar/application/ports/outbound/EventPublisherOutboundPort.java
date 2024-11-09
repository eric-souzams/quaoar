package dev.ericms.quaoar.application.ports.outbound;

import dev.ericms.quaoar.application.core.event.interfaces.DomainEvent;

public interface EventPublisherOutboundPort {

    void publishEvent(DomainEvent event);

}
