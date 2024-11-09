package dev.ericms.quaoar.infrastructure.events.registry;

import dev.ericms.quaoar.application.core.event.interfaces.DomainEvent;
import dev.ericms.quaoar.infrastructure.events.interfaces.EventPublisherStrategy;
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

    private final Map<Class<? extends DomainEvent>, EventPublisherStrategy<? extends DomainEvent>> strategies = new HashMap<>();

    @Autowired public EventPublisherRegistry(List<EventPublisherStrategy<? extends DomainEvent>> strategies) {
        for (EventPublisherStrategy<? extends DomainEvent> strategy : strategies) {
            this.strategies.put(getDomainEventType(strategy), strategy);
        }
    }

    private Class<? extends DomainEvent> getDomainEventType(EventPublisherStrategy strategy) {
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

//    private Class<? extends DomainEvent> getDomainEventType(EventPublisherStrategy strategy) {
//        return (Class<? extends DomainEvent>) Arrays.stream(strategy.getClass().getGenericInterfaces())
//               .filter(type -> type instanceof ParameterizedType)
//                .map(type -> (ParameterizedType) type)
//                .filter(type -> type.getRawType().equals(EventPublisherStrategy.class))
//                .map(type -> (Class<? extends DomainEvent>) type.getActualTypeArguments()[0])
//                .findFirst()
//                .orElseThrow(() -> new IllegalArgumentException("Strategy does not define a domain event type"));
//    }

    public EventPublisherStrategy getStrategy(Class<? extends DomainEvent> eventType) {
        return strategies.get(eventType);
    }
}
