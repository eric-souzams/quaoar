package dev.ericms.quaoar.application.core.usecase.topic;

import dev.ericms.quaoar.application.core.domain.Topic;
import dev.ericms.quaoar.application.ports.inbound.topic.SaveTopicInboundPort;
import dev.ericms.quaoar.application.ports.outbound.topic.SaveTopicOutboundPort;

public class SaveTopicUseCase implements SaveTopicInboundPort {

    private final SaveTopicOutboundPort saveTopicOutboundPort;

    public SaveTopicUseCase(SaveTopicOutboundPort saveTopicOutboundPort) {
        this.saveTopicOutboundPort = saveTopicOutboundPort;
    }

    @Override
    public Topic save(Topic topic) {
        return saveTopicOutboundPort.save(topic);
    }
}
