package dev.ericms.quaoar.adapters.outbound.adapter.message;

import dev.ericms.quaoar.adapters.outbound.mapper.MessageOutboundMapper;
import dev.ericms.quaoar.adapters.outbound.repository.MessageRepository;
import dev.ericms.quaoar.adapters.outbound.repository.entity.MessageEntity;
import dev.ericms.quaoar.application.core.domain.Message;
import dev.ericms.quaoar.application.core.domain.Topic;
import dev.ericms.quaoar.application.ports.outbound.message.SaveMessageOutboundPort;
import dev.ericms.quaoar.infrastructure.utils.JsonUtils;
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

        convertRecipientsStringToJson(messageEntity);

        MessageEntity response = messageRepository.save(messageEntity);

        List<String> topics = message.getTopics().stream()
                .map(Topic::getName)
                .filter(Objects::nonNull)
                .filter(name -> !name.isBlank())
                .toList();

        associateMessageToTopicAdapter.associate(response.getId(), topics);
    }

    private void convertRecipientsStringToJson(MessageEntity messageEntity) {
        messageEntity.setRecipientsTo(JsonUtils.convertEmailsToJsonString(messageEntity.getRecipientsTo()));
        messageEntity.setRecipientsCc(JsonUtils.convertEmailsToJsonString(messageEntity.getRecipientsCc()));
        messageEntity.setRecipientsBcc(JsonUtils.convertEmailsToJsonString(messageEntity.getRecipientsBcc()));
    }
}
