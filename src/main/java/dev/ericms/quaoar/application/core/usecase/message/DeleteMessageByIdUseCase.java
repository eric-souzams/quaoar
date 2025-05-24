package dev.ericms.quaoar.application.core.usecase.message;

import dev.ericms.quaoar.application.core.domain.Message;
import dev.ericms.quaoar.application.ports.inbound.message.DeleteMessageByIdInboundPort;
import dev.ericms.quaoar.application.ports.outbound.message.DeleteMessageOutboundPort;

public class DeleteMessageByIdUseCase implements DeleteMessageByIdInboundPort {

    private final DeleteMessageOutboundPort deleteMessageOutboundPort;

    public DeleteMessageByIdUseCase(DeleteMessageOutboundPort deleteMessageOutboundPort) {
        this.deleteMessageOutboundPort = deleteMessageOutboundPort;
    }

    @Override
    public void delete(Message message) {
        deleteMessageOutboundPort.delete(message);
    }
}
