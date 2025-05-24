package dev.ericms.quaoar.adapters.outbound.adapter.message;

import dev.ericms.quaoar.adapters.outbound.mapper.MessageOutboundMapper;
import dev.ericms.quaoar.adapters.outbound.repository.MessageTopicRepository;
import dev.ericms.quaoar.adapters.outbound.repository.entity.MessageEntity;
import dev.ericms.quaoar.application.core.domain.Message;
import dev.ericms.quaoar.application.ports.outbound.message.FindMessageByTopicIdOutboundPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Component
public class FindMessageByTopicIdAdapter implements FindMessageByTopicIdOutboundPort {

    @Autowired
    private MessageTopicRepository messageTopicRepository;

    @Autowired
    private MessageOutboundMapper messageOutboundMapper;

    @Override
    @Transactional(readOnly = true)
    public List<Message> find(UUID topicId) {
        List<MessageEntity> messagesFounded = messageTopicRepository.findMessagesByTopicId(topicId);

        return messagesFounded.stream()
                .map(message -> messageOutboundMapper.toDomain(message))
                .toList();
    }
}
