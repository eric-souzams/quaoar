package dev.ericms.quaoar.application.core.usecase.contact;

import dev.ericms.quaoar.application.core.domain.Contact;
import dev.ericms.quaoar.application.ports.inbound.DeleteContactInboundPort;
import dev.ericms.quaoar.application.ports.outbound.DeleteContactOutboundPort;

public class DeleteContactUseCase implements DeleteContactInboundPort {

    private final DeleteContactOutboundPort deleteContactOutboundPort;

    public DeleteContactUseCase(DeleteContactOutboundPort deleteContactOutboundPort) {
        this.deleteContactOutboundPort = deleteContactOutboundPort;
    }

    @Override
    public void delete(Contact contact) {
        deleteContactOutboundPort.delete(contact);
    }
}
