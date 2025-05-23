package dev.ericms.quaoar.adapters.outbound.adapter.message;

import dev.ericms.quaoar.adapters.outbound.mapper.TopicOutboundMapper;
import dev.ericms.quaoar.adapters.outbound.repository.MessageRepository;
import dev.ericms.quaoar.adapters.outbound.repository.MessageTopicRepository;
import dev.ericms.quaoar.adapters.outbound.repository.TopicRepository;
import dev.ericms.quaoar.adapters.outbound.repository.entity.MessageTopicEntity;
import dev.ericms.quaoar.adapters.outbound.repository.entity.TopicEntity;
import dev.ericms.quaoar.application.core.domain.Topic;
import dev.ericms.quaoar.application.ports.outbound.message.AssociateMessageToTopicOutboundPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class AssociateMessageToTopicAdapter implements AssociateMessageToTopicOutboundPort {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private MessageTopicRepository messageTopicRepository;

    @Autowired
    private TopicOutboundMapper topicOutboundMapper;

    @Override
    @Transactional
    public void associate(UUID messageId, List<String> topics) {
        List<TopicEntity> allTopics = getAllTopics(topics);

        List<MessageTopicEntity> links = allTopics.stream()
                .map(topic -> new MessageTopicEntity(messageId, topic.getId()))
                .toList();

        messageTopicRepository.saveAll(links);
    }

    private List<TopicEntity> getAllTopics(List<String> topics) {
        List<TopicEntity> allTopics = new ArrayList<>();

        List<TopicEntity> foundedTopics = topicRepository.findByNameIn(topics);
        List<TopicEntity> createdTopics = createNotFoundTopics(topics, foundedTopics);

        allTopics.addAll(foundedTopics);
        allTopics.addAll(createdTopics);

        return allTopics;
    }

    private List<TopicEntity> createNotFoundTopics(List<String> topics, List<TopicEntity> foundedTopics) {
        Map<String, TopicEntity> existingMap = foundedTopics.stream()
                .collect(Collectors.toMap(TopicEntity::getName, Function.identity()));

        List<Topic> topicsToCreate = topics.stream()
                .filter(topicName -> !existingMap.containsKey(topicName))
                .map(topicName -> createTopicBuilder(topicName, new Topic()))
                .toList();

        List<TopicEntity> topicsEntityToSave = topicsToCreate.stream()
                .map(topic -> topicOutboundMapper.toEntity(topic))
                        .toList();

        return topicRepository.saveAll(topicsEntityToSave);
    }

    private Topic createTopicBuilder(String topicName, Topic topic) {
        topic.setName(topicName);
        topic.setActive(true);

        return topic;
    }
}
