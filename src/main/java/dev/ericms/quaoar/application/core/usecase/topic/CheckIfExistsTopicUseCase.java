package dev.ericms.quaoar.application.core.usecase.topic;

import dev.ericms.quaoar.application.ports.inbound.topic.CheckIfExistsTopicInboundPort;
import dev.ericms.quaoar.application.ports.outbound.topic.CheckIfExistsTopicOutboundPort;

public class CheckIfExistsTopicUseCase implements CheckIfExistsTopicInboundPort {

    private final CheckIfExistsTopicOutboundPort checkIfExistsTopicOutboundPort;

    public CheckIfExistsTopicUseCase(CheckIfExistsTopicOutboundPort checkIfExistsTopicOutboundPort) {
        this.checkIfExistsTopicOutboundPort = checkIfExistsTopicOutboundPort;
    }

    @Override
    public boolean check(String topic) {
        return checkIfExistsTopicOutboundPort.check(topic);
    }
}
