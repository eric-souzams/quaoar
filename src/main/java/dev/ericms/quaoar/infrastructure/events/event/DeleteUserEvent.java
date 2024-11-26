package dev.ericms.quaoar.infrastructure.events.event;

import dev.ericms.quaoar.application.core.events.DeleteUserDomainEvent;
import org.springframework.context.ApplicationEvent;

public class DeleteUserEvent extends ApplicationEvent {

    private final DeleteUserDomainEvent deleteUserDomainEvent;

    public DeleteUserEvent(Object source, DeleteUserDomainEvent deleteUserDomainEvent) {
        super(source);
        this.deleteUserDomainEvent = deleteUserDomainEvent;
    }

    public DeleteUserDomainEvent getBody() {
        return deleteUserDomainEvent;
    }
}
