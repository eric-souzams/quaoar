package dev.ericms.quaoar.infrastructure.events.event;

import dev.ericms.quaoar.application.core.events.SendEmailDomainEvent;
import org.springframework.context.ApplicationEvent;

public class SendEmailEvent extends ApplicationEvent {

    private final SendEmailDomainEvent sendEmailDomainEvent;

    public SendEmailEvent(Object source, SendEmailDomainEvent sendEmailDomainEvent) {
        super(source);
        this.sendEmailDomainEvent = sendEmailDomainEvent;
    }

    public SendEmailDomainEvent getBody() {
        return sendEmailDomainEvent;
    }
}
