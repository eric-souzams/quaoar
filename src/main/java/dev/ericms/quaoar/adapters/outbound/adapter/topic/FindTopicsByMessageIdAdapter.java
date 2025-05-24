package dev.ericms.quaoar.adapters.outbound.adapter.topic;

import dev.ericms.quaoar.adapters.outbound.mapper.TopicOutboundMapper;
import dev.ericms.quaoar.adapters.outbound.repository.MessageTopicRepository;
import dev.ericms.quaoar.adapters.outbound.repository.entity.TopicEntity;
import dev.ericms.quaoar.application.core.domain.Topic;
import dev.ericms.quaoar.application.ports.outbound.topic.FindTopicsByMessageIdOutboundPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Component
public class FindTopicsByMessageIdAdapter implements FindTopicsByMessageIdOutboundPort {

    @Autowired
    private MessageTopicRepository messageTopicRepository;

    @Autowired
    private TopicOutboundMapper topicOutboundMapper;

    @Override
    @Transactional(readOnly = true)
    public List<Topic> find(UUID messageId) {
        List<TopicEntity> topicsFounded = messageTopicRepository.findTopicsByMessageId(messageId);

        return topicsFounded.stream()
                .map(topic -> topicOutboundMapper.toDomain(topic))
                .toList();
    }
}
