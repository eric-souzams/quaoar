package dev.ericms.quaoar.application.core.usecase.topic;

import dev.ericms.quaoar.application.core.domain.Topic;
import dev.ericms.quaoar.application.core.exception.BusinessException;
import dev.ericms.quaoar.application.ports.inbound.topic.FindTopicByNameInboundPort;
import dev.ericms.quaoar.application.ports.outbound.topic.FindTopicByNameOutboundPort;

import static dev.ericms.quaoar.application.core.utils.Constants.TOPIC_NOT_FOUND;

public class FindTopicByNameUseCase implements FindTopicByNameInboundPort {

    private final FindTopicByNameOutboundPort findTopicByNameOutboundPort;

    public FindTopicByNameUseCase(FindTopicByNameOutboundPort findTopicByNameOutboundPort) {
        this.findTopicByNameOutboundPort = findTopicByNameOutboundPort;
    }

    @Override
    public Topic find(String topic) {
        return findTopicByNameOutboundPort.find(topic)
                .orElseThrow(() -> new BusinessException(TOPIC_NOT_FOUND.getMessage()));
    }
}
