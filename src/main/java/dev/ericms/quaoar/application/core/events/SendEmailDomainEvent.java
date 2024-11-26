package dev.ericms.quaoar.application.core.events;

import dev.ericms.quaoar.application.core.dto.SendEmailDto;
import dev.ericms.quaoar.application.core.events.interfaces.DomainEvent;

public class SendEmailDomainEvent implements DomainEvent {

    private final SendEmailDto emailDto;

    public SendEmailDomainEvent(SendEmailDto emailDto) {
        this.emailDto = emailDto;
    }

    public SendEmailDto getBody() {
        return emailDto;
    }

    @Override
    public String toString() {
        return "SendEmailDomainEvent{" +
                "emailDto=" + emailDto +
                '}';
    }
}
