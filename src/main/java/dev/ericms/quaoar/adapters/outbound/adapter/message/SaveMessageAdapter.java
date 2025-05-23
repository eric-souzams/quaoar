package dev.ericms.quaoar.adapters.outbound.adapter.message;

import dev.ericms.quaoar.adapters.outbound.mapper.MessageOutboundMapper;
import dev.ericms.quaoar.adapters.outbound.repository.MessageRepository;
import dev.ericms.quaoar.adapters.outbound.repository.entity.MessageEntity;
import dev.ericms.quaoar.application.core.domain.Message;
import dev.ericms.quaoar.application.ports.outbound.message.SaveMessageOutboundPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class SaveMessageAdapter implements SaveMessageOutboundPort {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private MessageOutboundMapper messageOutboundMapper;

    @Override
    @Transactional
    public void save(Message message) {
        MessageEntity messageEntity = messageOutboundMapper.toEntity(message);

        messageRepository.save(messageEntity);
    }
}
