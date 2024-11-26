package dev.ericms.quaoar.infrastructure.events.registry;

import dev.ericms.quaoar.application.core.events.interfaces.DomainEvent;
import dev.ericms.quaoar.infrastructure.events.interfaces.EventPublisherStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class EventPublisherRegistry {

    private static final Logger logger = LoggerFactory.getLogger(EventPublisherRegistry.class);

    private final Map<Class<? extends DomainEvent>, EventPublisherStrategy<? extends DomainEvent>> strategies = new HashMap<>();

    @Autowired public EventPublisherRegistry(List<EventPublisherStrategy<? extends DomainEvent>> strategies) {
        for (EventPublisherStrategy<? extends DomainEvent> strategy : strategies) {
            Class<? extends DomainEvent> eventType = getDomainEventType(strategy);
            this.strategies.put(eventType, strategy);
            logger.info("Registered strategy '{}' for event type '{}'", strategy.getClass().getSimpleName(), eventType.getSimpleName());
        }
        logger.info("{} strategy(ies) have been registered for the publisher", strategies.size());
    }

    private Class<? extends DomainEvent> getDomainEventType(EventPublisherStrategy<? extends DomainEvent> strategy) {
        return Arrays.stream(strategy.getClass().getGenericInterfaces())
                .filter(type -> type instanceof ParameterizedType)
                .map(type -> (ParameterizedType) type)
                .filter(type -> type.getRawType().equals(EventPublisherStrategy.class))
                .map(type -> {
                    Type actualType = type.getActualTypeArguments()[0];
                    if (actualType instanceof Class<?> && DomainEvent.class.isAssignableFrom((Class<?>) actualType)) {
                        @SuppressWarnings("unchecked")
                        Class<? extends DomainEvent> domainEventType = (Class<? extends DomainEvent>) actualType;
                        return domainEventType;
                    } else {
                        throw new IllegalArgumentException("Domain event type is not a class or is not supported");
                    }
                })
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Strategy does not define a domain event type"));
    }

    public EventPublisherStrategy<? extends DomainEvent> getStrategy(Class<? extends DomainEvent> eventType) {
        return strategies.get(eventType);
    }
}
