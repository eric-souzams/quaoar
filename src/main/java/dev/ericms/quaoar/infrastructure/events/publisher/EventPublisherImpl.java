package dev.ericms.quaoar.infrastructure.events.publisher;

import dev.ericms.quaoar.application.core.events.interfaces.DomainEvent;
import dev.ericms.quaoar.application.ports.outbound.events.EventPublisherOutboundPort;
import dev.ericms.quaoar.infrastructure.events.interfaces.EventPublisherStrategy;
import dev.ericms.quaoar.infrastructure.events.registry.EventPublisherRegistry;
import org.springframework.stereotype.Component;

import static dev.ericms.quaoar.infrastructure.utils.Validation.isNotNull;

@Component
public class EventPublisherImpl implements EventPublisherOutboundPort {

    private final EventPublisherRegistry registry;

    public EventPublisherImpl(EventPublisherRegistry registry) {
        this.registry = registry;
    }

    @Override
    public void publishEvent(DomainEvent event) {
        EventPublisherStrategy<? extends DomainEvent> strategy = getStrategy(event);

        if (isNotNull(strategy)) {
            strategy.publish(event);
        } else {
            throw new IllegalArgumentException("No strategy found for event type: " + event.getClass());
        }
    }

    private EventPublisherStrategy<? extends DomainEvent> getStrategy(DomainEvent event) {
        return registry.getStrategy(event.getClass());
    }
}
