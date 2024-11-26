package dev.ericms.quaoar.infrastructure.events.event;

import dev.ericms.quaoar.application.core.events.ChangeUserInfoDomainEvent;
import dev.ericms.quaoar.application.core.events.SendEmailDomainEvent;
import org.springframework.context.ApplicationEvent;

public class ChangeUserInfoEvent extends ApplicationEvent {

    private final ChangeUserInfoDomainEvent changeUserInfoDomainEvent;

    public ChangeUserInfoEvent(Object source, ChangeUserInfoDomainEvent changeUserInfoDomainEvent) {
        super(source);
        this.changeUserInfoDomainEvent = changeUserInfoDomainEvent;
    }

    public ChangeUserInfoDomainEvent getBody() {
        return changeUserInfoDomainEvent;
    }
}
