package dev.ericms.quaoar.application.core.usecase.message;

import dev.ericms.quaoar.application.core.domain.Message;
import dev.ericms.quaoar.application.ports.inbound.message.DeleteMessageByIdInboundPort;
import dev.ericms.quaoar.application.ports.inbound.message.FindMessageByIdInboundPort;
import dev.ericms.quaoar.application.ports.outbound.message.DeleteMessageOutboundPort;

import java.util.UUID;

public class DeleteMessageByIdUseCase implements DeleteMessageByIdInboundPort {

    private final DeleteMessageOutboundPort deleteMessageOutboundPort;

    private final FindMessageByIdInboundPort findMessageByIdInboundPort;

    public DeleteMessageByIdUseCase(DeleteMessageOutboundPort deleteMessageOutboundPort,
                                    FindMessageByIdInboundPort findMessageByIdInboundPort) {
        this.deleteMessageOutboundPort = deleteMessageOutboundPort;
        this.findMessageByIdInboundPort = findMessageByIdInboundPort;
    }

    @Override
    public void delete(UUID messageId) {
        Message message = findMessageByIdInboundPort.find(messageId);

        deleteMessageOutboundPort.delete(message);
    }
}
