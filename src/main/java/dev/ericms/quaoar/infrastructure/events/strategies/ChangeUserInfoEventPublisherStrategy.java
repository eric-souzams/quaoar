package dev.ericms.quaoar.infrastructure.events.strategies;

import dev.ericms.quaoar.application.core.events.ChangeUserInfoDomainEvent;
import dev.ericms.quaoar.application.core.events.interfaces.DomainEvent;
import dev.ericms.quaoar.infrastructure.events.event.ChangeUserInfoEvent;
import dev.ericms.quaoar.infrastructure.events.interfaces.EventPublisherStrategy;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class ChangeUserInfoEventPublisherStrategy implements EventPublisherStrategy<ChangeUserInfoDomainEvent> {

    private final ApplicationEventPublisher publisher;

    public ChangeUserInfoEventPublisherStrategy(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public void publish(DomainEvent event) {
        if (event instanceof ChangeUserInfoDomainEvent) {
            ChangeUserInfoEvent changeUserInfoEvent = new ChangeUserInfoEvent(this, (ChangeUserInfoDomainEvent) event);
            publisher.publishEvent(changeUserInfoEvent);
        }
    }
}
