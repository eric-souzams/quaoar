package dev.ericms.quaoar.application.core.events;

import dev.ericms.quaoar.application.core.dto.ChangeUserInfoDto;
import dev.ericms.quaoar.application.core.events.interfaces.DomainEvent;

public class ChangeUserInfoDomainEvent implements DomainEvent {

    private final ChangeUserInfoDto changeUserInfoDto;

    public ChangeUserInfoDomainEvent(ChangeUserInfoDto changeUserInfoDto) {
        this.changeUserInfoDto = changeUserInfoDto;
    }

    public ChangeUserInfoDto getBody() {
        return changeUserInfoDto;
    }

    @Override
    public String toString() {
        return "ChangeUserInfoDomainEvent{" +
                "changeUserInfoDto=" + changeUserInfoDto +
                '}';
    }
}
