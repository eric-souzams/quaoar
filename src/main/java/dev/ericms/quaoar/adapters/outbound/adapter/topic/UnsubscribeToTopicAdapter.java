package dev.ericms.quaoar.adapters.outbound.adapter.topic;

import dev.ericms.quaoar.adapters.outbound.repository.ContactRepository;
import dev.ericms.quaoar.adapters.outbound.repository.TopicRepository;
import dev.ericms.quaoar.adapters.outbound.repository.entity.ContactEntity;
import dev.ericms.quaoar.adapters.outbound.repository.entity.TopicEntity;
import dev.ericms.quaoar.application.core.exception.BusinessException;
import dev.ericms.quaoar.application.ports.outbound.topic.UnsubscribeToTopicOutbound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static dev.ericms.quaoar.application.core.utils.Constants.EMAIL_ADDRESS_NOT_FOUND;
import static dev.ericms.quaoar.application.core.utils.Constants.TOPIC_NOT_FOUND;

@Component
public class UnsubscribeToTopicAdapter implements UnsubscribeToTopicOutbound {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private TopicRepository topicRepository;

    @Override
    @Transactional
    public void unsubscribe(String topic, String email) {
        ContactEntity contactEntity = getContactEntity(email);
        TopicEntity topicEntity = getTopicEntity(topic);

        contactEntity.getTopics().remove(topicEntity);
    }

    private TopicEntity getTopicEntity(String topic) {
        return topicRepository.findByName(topic)
                .orElseThrow(() -> new BusinessException(TOPIC_NOT_FOUND.getMessage()));
    }

    private ContactEntity getContactEntity(String email) {
        return contactRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessException(EMAIL_ADDRESS_NOT_FOUND.getMessage()));
    }
}
