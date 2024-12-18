package dev.ericms.quaoar.application.core.usecase.contact;

import dev.ericms.quaoar.application.core.domain.Contact;
import dev.ericms.quaoar.application.ports.inbound.contact.SaveContactInboundPort;
import dev.ericms.quaoar.application.ports.outbound.contact.SaveContactOutboundPort;

public class SaveContactUseCase implements SaveContactInboundPort {

    private final SaveContactOutboundPort saveContactOutboundPort;

    public SaveContactUseCase(SaveContactOutboundPort saveContactOutboundPort) {
        this.saveContactOutboundPort = saveContactOutboundPort;
    }

    @Override
    public void save(Contact contact) {
        saveContactOutboundPort.save(contact);
    }
}
