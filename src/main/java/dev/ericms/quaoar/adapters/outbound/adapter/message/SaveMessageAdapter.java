package dev.ericms.quaoar.adapters.outbound.adapter.message;

import dev.ericms.quaoar.adapters.outbound.mapper.MessageOutboundMapper;
import dev.ericms.quaoar.adapters.outbound.repository.MessageRepository;
import dev.ericms.quaoar.adapters.outbound.repository.entity.MessageEntity;
import dev.ericms.quaoar.application.core.domain.AbstractTopic;
import dev.ericms.quaoar.application.core.domain.Message;
import dev.ericms.quaoar.application.ports.outbound.message.SaveMessageOutboundPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Component
public class SaveMessageAdapter implements SaveMessageOutboundPort {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private MessageOutboundMapper messageOutboundMapper;

    @Autowired
    private AssociateMessageToTopicAdapter associateMessageToTopicAdapter;

    @Override
    @Transactional
    public void save(Message message) {
        MessageEntity messageEntity = messageOutboundMapper.toEntity(message);

        MessageEntity response = messageRepository.save(messageEntity);

        List<String> topics = message.getTopics().stream()
                .map(AbstractTopic::getName)
                .filter(Objects::nonNull)
                .filter(name -> !name.isBlank())
                .toList();

        associateMessageToTopicAdapter.associate(response.getId(), topics);
    }
}
