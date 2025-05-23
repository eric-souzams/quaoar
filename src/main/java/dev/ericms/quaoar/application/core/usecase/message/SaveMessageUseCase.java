package dev.ericms.quaoar.application.core.usecase.message;

import dev.ericms.quaoar.application.core.domain.Message;
import dev.ericms.quaoar.application.ports.inbound.message.SaveMessageInboundPort;
import dev.ericms.quaoar.application.ports.outbound.message.SaveMessageOutboundPort;

public class SaveMessageUseCase implements SaveMessageInboundPort {

    private final SaveMessageOutboundPort saveMessageOutboundPort;

    public SaveMessageUseCase(SaveMessageOutboundPort saveMessageOutboundPort) {
        this.saveMessageOutboundPort = saveMessageOutboundPort;
    }

    @Override
    public void save(Message message) {
        saveMessageOutboundPort.save(message);
    }
}
