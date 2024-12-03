package dev.ericms.quaoar.adapters.inbound.listeners;

import dev.ericms.quaoar.application.core.domain.Contact;
import dev.ericms.quaoar.application.core.dto.ChangeUserInfoDto;
import dev.ericms.quaoar.application.core.exception.BusinessException;
import dev.ericms.quaoar.application.ports.inbound.CheckIfExistsContactEmailInboundPort;
import dev.ericms.quaoar.application.ports.inbound.FindContactByEmailInboundPort;
import dev.ericms.quaoar.application.ports.inbound.SaveContactInboundPort;
import dev.ericms.quaoar.infrastructure.events.event.ChangeUserInfoEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import static dev.ericms.quaoar.application.core.utils.Constants.INVALID_OBJECT;

@Component
public class ChangeUserInfoListener {

    private static final Logger logger = LoggerFactory.getLogger(ChangeUserInfoListener.class);

    @Autowired
    private SaveContactInboundPort saveContactInboundPort;

    @Autowired
    private CheckIfExistsContactEmailInboundPort checkIfExistsContactEmailInboundPort;

    @Autowired
    private FindContactByEmailInboundPort findContactByEmailInboundPort;

    @EventListener
    public void handler(ChangeUserInfoEvent event) {
        logger.info("New event received: {}", event.getBody());

        try {
            ChangeUserInfoDto payload = getPayload(event);
            if (checkIfExistsContactEmailInboundPort.check(payload.getEmail())) {
                //if exists, update
                Contact contact = findContactByEmailInboundPort.find(payload.getEmail());
                saveContactInboundPort.save(createOrUpdateContract(payload, contact));
                logger.info("Updated contact from e-mail address: {}", payload.getEmail());
            } else {
                //if not exists, create
                saveContactInboundPort.save(createOrUpdateContract(payload, new Contact()));
                logger.info("Created contact from e-mail address: {}", payload.getEmail());
            }
        } catch (BusinessException e) {
            logger.error("Error to process event: {}", e.getMessage());
        }
    }

    private ChangeUserInfoDto getPayload(ChangeUserInfoEvent event) {
        if (event.getBody().getBody() != null) {
            return event.getBody().getBody();
        }

        throw new BusinessException(INVALID_OBJECT.getMessage());
    }

    private Contact createOrUpdateContract(ChangeUserInfoDto payload, Contact contact) {
        contact.setEmail(payload.getEmail());
        contact.setName(payload.getName());
        contact.setIntegrationId(payload.getIntegrationId());
        contact.setBlocked(payload.getBlocked());

        return contact;
    }
}
