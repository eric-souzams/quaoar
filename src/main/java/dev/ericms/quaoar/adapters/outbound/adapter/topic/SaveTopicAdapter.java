package dev.ericms.quaoar.adapters.outbound.adapter.topic;

import dev.ericms.quaoar.adapters.outbound.mapper.TopicOutboundMapper;
import dev.ericms.quaoar.adapters.outbound.repository.TopicRepository;
import dev.ericms.quaoar.adapters.outbound.repository.entity.TopicEntity;
import dev.ericms.quaoar.application.core.domain.Topic;
import dev.ericms.quaoar.application.ports.outbound.topic.SaveTopicOutboundPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class SaveTopicAdapter implements SaveTopicOutboundPort {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private TopicOutboundMapper topicOutboundMapper;

    @Override
    @Transactional
    public Topic save(Topic topic) {
        TopicEntity topicEntity = topicOutboundMapper.toEntity(topic);

        return topicOutboundMapper.toDomain(topicRepository.save(topicEntity));
    }
}
