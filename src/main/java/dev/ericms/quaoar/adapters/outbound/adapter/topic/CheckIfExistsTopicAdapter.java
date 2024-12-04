package dev.ericms.quaoar.adapters.outbound.adapter.topic;

import dev.ericms.quaoar.adapters.outbound.repository.TopicRepository;
import dev.ericms.quaoar.application.ports.outbound.topic.CheckIfExistsTopicOutboundPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CheckIfExistsTopicAdapter implements CheckIfExistsTopicOutboundPort {

    @Autowired
    private TopicRepository topicRepository;

    @Override
    @Transactional(readOnly = true)
    public boolean check(String topic) {
        return topicRepository.existsByName(topic);
    }
}
