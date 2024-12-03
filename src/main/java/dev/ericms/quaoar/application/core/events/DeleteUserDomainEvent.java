package dev.ericms.quaoar.application.core.events;

import dev.ericms.quaoar.application.core.dto.DeleteUserDto;
import dev.ericms.quaoar.application.core.events.interfaces.DomainEvent;

public class DeleteUserDomainEvent implements DomainEvent {

    private final DeleteUserDto deleteUserDto;

    public DeleteUserDomainEvent(DeleteUserDto deleteUserDto) {
        this.deleteUserDto = deleteUserDto;
    }

    public DeleteUserDto getBody() {
        return deleteUserDto;
    }

    @Override
    public String toString() {
        return "DeleteUserDomainEvent{" +
                "deleteUserDto=" + deleteUserDto +
                '}';
    }
}
