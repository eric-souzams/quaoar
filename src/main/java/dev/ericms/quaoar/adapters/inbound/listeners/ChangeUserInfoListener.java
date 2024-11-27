package dev.ericms.quaoar.adapters.inbound.listeners;

import dev.ericms.quaoar.application.ports.inbound.SaveContactInboundPort;
import dev.ericms.quaoar.infrastructure.events.event.ChangeUserInfoEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ChangeUserInfoListener {

    private static final Logger logger = LoggerFactory.getLogger(ChangeUserInfoListener.class);

    @Autowired
    private SaveContactInboundPort saveContactInboundPort;

    @EventListener
    public void handler(ChangeUserInfoEvent event) {
        logger.info("New event received: {}", event.getBody());
    }

}
