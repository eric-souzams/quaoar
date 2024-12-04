package dev.ericms.quaoar.adapters.outbound.adapter.topic;

import dev.ericms.quaoar.adapters.outbound.mapper.TopicMapper;
import dev.ericms.quaoar.adapters.outbound.repository.TopicRepository;
import dev.ericms.quaoar.adapters.outbound.repository.entity.TopicEntity;
import dev.ericms.quaoar.application.core.domain.Topic;
import dev.ericms.quaoar.application.ports.outbound.topic.FindTopicByNameOutboundPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class FindTopicByNameAdapter implements FindTopicByNameOutboundPort {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private TopicMapper topicMapper;

    @Override
    @Transactional(readOnly = true)
    public Optional<Topic> find(String topic) {
        Optional<TopicEntity> topicEntity = topicRepository.findByName(topic);

        return topicEntity.map(entity -> topicMapper.toDomain(entity));
    }
}
