package dev.ericms.quaoar.application.core.usecase.contact;

import dev.ericms.quaoar.application.core.domain.Contact;
import dev.ericms.quaoar.application.core.exception.BusinessException;
import dev.ericms.quaoar.application.ports.inbound.CheckIfExistsContactEmailInboundPort;
import dev.ericms.quaoar.application.ports.inbound.SaveContactInboundPort;
import dev.ericms.quaoar.application.ports.outbound.SaveContactOutboundPort;

import static dev.ericms.quaoar.application.core.utils.Constants.EMAIL_ADDRESS_ALREADY_REGISTERED;

public class SaveContactUseCase implements SaveContactInboundPort {

    private final CheckIfExistsContactEmailInboundPort checkIfExistsContactEmailInboundPort;

    private final SaveContactOutboundPort saveContactOutboundPort;

    public SaveContactUseCase(CheckIfExistsContactEmailInboundPort checkIfExistsContactEmailInboundPort, SaveContactOutboundPort saveContactOutboundPort) {
        this.checkIfExistsContactEmailInboundPort = checkIfExistsContactEmailInboundPort;
        this.saveContactOutboundPort = saveContactOutboundPort;
    }

    @Override
    public void save(Contact contact) {
        if (checkIfExistsContactEmailInboundPort.check(contact.getEmail())) {
            throw new BusinessException(EMAIL_ADDRESS_ALREADY_REGISTERED.getMessage());
        }

        saveContactOutboundPort.save(contact);
    }
}
