package dev.ericms.quaoar.adapters.inbound.listeners;

import dev.ericms.quaoar.application.core.domain.Contact;
import dev.ericms.quaoar.application.core.dto.DeleteUserDto;
import dev.ericms.quaoar.application.core.exception.BusinessException;
import dev.ericms.quaoar.application.ports.inbound.contact.CheckIfExistsContactEmailInboundPort;
import dev.ericms.quaoar.application.ports.inbound.contact.DeleteContactInboundPort;
import dev.ericms.quaoar.application.ports.inbound.contact.FindContactByEmailInboundPort;
import dev.ericms.quaoar.infrastructure.events.event.DeleteUserEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import static dev.ericms.quaoar.application.core.utils.Constants.INVALID_OBJECT;

@Component
public class DeleteUserListener {

    private static final Logger logger = LoggerFactory.getLogger(DeleteUserListener.class);

    @Autowired
    private CheckIfExistsContactEmailInboundPort checkIfExistsContactEmailInboundPort;

    @Autowired
    private FindContactByEmailInboundPort findContactByEmailInboundPort;

    @Autowired
    private DeleteContactInboundPort deleteContactInboundPort;

    @EventListener
    public void handler(DeleteUserEvent event) {
        logger.info("New event received: {}", event.getBody());

       try {
           DeleteUserDto payload = getPayload(event);

           Contact contact = findContactByEmailInboundPort.find(payload.getEmail());

           logger.info("Deleting contact: {}", payload.getEmail());
           deleteContactInboundPort.delete(contact);
       } catch (BusinessException e) {
           logger.error("Error to process event: {}", e.getMessage());
       }
    }

    private DeleteUserDto getPayload(DeleteUserEvent event) {
        if (event.getBody().getBody() != null) {
            return event.getBody().getBody();
        }

        throw new BusinessException(INVALID_OBJECT.getMessage());
    }

}
