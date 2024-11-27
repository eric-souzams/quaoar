package dev.ericms.quaoar.application.core.usecase.contact;

import dev.ericms.quaoar.application.ports.inbound.CheckIfExistsContactEmailInboundPort;
import dev.ericms.quaoar.application.ports.outbound.CheckIfExistsContactEmailOutboundPort;

public class CheckIfExistsContactEmailUseCase implements CheckIfExistsContactEmailInboundPort {

    private final CheckIfExistsContactEmailOutboundPort checkIfExistsContactEmailOutboundPort;

    public CheckIfExistsContactEmailUseCase(CheckIfExistsContactEmailOutboundPort checkIfExistsContactEmailOutboundPort) {
        this.checkIfExistsContactEmailOutboundPort = checkIfExistsContactEmailOutboundPort;
    }

    @Override
    public boolean check(String email) {
        return checkIfExistsContactEmailOutboundPort.check(email);
    }
}
