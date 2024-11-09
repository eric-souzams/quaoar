package dev.ericms.quaoar.infrastructure.events.listener;

import dev.ericms.quaoar.infrastructure.events.event.SendEmailEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EmailListener {

    @EventListener
    public void handleSendEmail(SendEmailEvent event) {
        //TODO
    }

}
