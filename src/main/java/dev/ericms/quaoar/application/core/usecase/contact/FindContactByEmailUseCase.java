package dev.ericms.quaoar.application.core.usecase.contact;

import dev.ericms.quaoar.application.core.domain.Contact;
import dev.ericms.quaoar.application.core.exception.BusinessException;
import dev.ericms.quaoar.application.ports.inbound.FindContactByEmailInboundPort;
import dev.ericms.quaoar.application.ports.outbound.FindContactByEmailOutboundPort;

import static dev.ericms.quaoar.application.core.utils.Constants.EMAIL_ADDRESS_NOT_FOUND;

public class FindContactByEmailUseCase implements FindContactByEmailInboundPort {

    private final FindContactByEmailOutboundPort findContactByEmailOutboundPort;

    public FindContactByEmailUseCase(FindContactByEmailOutboundPort findContactByEmailOutboundPort) {
        this.findContactByEmailOutboundPort = findContactByEmailOutboundPort;
    }

    @Override
    public Contact find(String email) {
        return findContactByEmailOutboundPort.find(email)
                .orElseThrow(() -> new BusinessException(EMAIL_ADDRESS_NOT_FOUND.getMessage()));
    }
}
