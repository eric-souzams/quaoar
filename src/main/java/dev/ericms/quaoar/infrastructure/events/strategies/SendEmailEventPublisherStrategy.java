package dev.ericms.quaoar.infrastructure.events.strategies;

import dev.ericms.quaoar.application.core.events.SendEmailDomainEvent;
import dev.ericms.quaoar.application.core.events.interfaces.DomainEvent;
import dev.ericms.quaoar.infrastructure.events.event.SendEmailEvent;
import dev.ericms.quaoar.infrastructure.events.interfaces.EventPublisherStrategy;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class SendEmailEventPublisherStrategy implements EventPublisherStrategy<SendEmailDomainEvent> {

    private final ApplicationEventPublisher publisher;

    public SendEmailEventPublisherStrategy(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public void publish(DomainEvent event) {
        if (event instanceof SendEmailDomainEvent) {
            SendEmailEvent sendEmailEvent = new SendEmailEvent(this, (SendEmailDomainEvent) event);
            publisher.publishEvent(sendEmailEvent);
        }
    }
}
