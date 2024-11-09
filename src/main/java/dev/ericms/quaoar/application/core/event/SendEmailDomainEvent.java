package dev.ericms.quaoar.application.core.event;

import dev.ericms.quaoar.application.core.dto.SendEmailDto;
import dev.ericms.quaoar.application.core.event.interfaces.DomainEvent;

public class SendEmailDomainEvent implements DomainEvent {

    private final SendEmailDto emailDto;

    public SendEmailDomainEvent(SendEmailDto emailDto) {
        this.emailDto = emailDto;
    }

    public SendEmailDto getBody() {
        return emailDto;
    }
}
