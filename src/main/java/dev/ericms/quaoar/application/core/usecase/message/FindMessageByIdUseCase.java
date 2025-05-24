package dev.ericms.quaoar.application.core.usecase.message;

import dev.ericms.quaoar.application.core.domain.Message;
import dev.ericms.quaoar.application.core.domain.Topic;
import dev.ericms.quaoar.application.core.exception.BusinessException;
import dev.ericms.quaoar.application.ports.inbound.message.FindMessageByIdInboundPort;
import dev.ericms.quaoar.application.ports.outbound.message.FindMessageByIdOutboundPort;
import dev.ericms.quaoar.application.ports.outbound.topic.FindTopicsByMessageIdOutboundPort;

import java.util.List;
import java.util.UUID;

import static dev.ericms.quaoar.application.core.utils.Constants.MESSAGE_NOT_FOUND;

public class FindMessageByIdUseCase implements FindMessageByIdInboundPort {

    private final FindMessageByIdOutboundPort findMessageByIdOutboundPort;

    private final FindTopicsByMessageIdOutboundPort findTopicsByMessageIdOutboundPort;

    public FindMessageByIdUseCase(FindMessageByIdOutboundPort findMessageByIdOutboundPort,
                                  FindTopicsByMessageIdOutboundPort findTopicsByMessageIdOutboundPort) {
        this.findMessageByIdOutboundPort = findMessageByIdOutboundPort;
        this.findTopicsByMessageIdOutboundPort = findTopicsByMessageIdOutboundPort;
    }

    @Override
    public Message find(UUID messageId) {
        Message message = findMessageByIdOutboundPort.find(messageId)
                .orElseThrow(() -> new BusinessException(MESSAGE_NOT_FOUND.getMessage()));

        List<Topic> topics = findTopicsByMessageIdOutboundPort.find(messageId);
        message.setTopics(topics);

        return message;
    }
}
