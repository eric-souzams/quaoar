package dev.ericms.quaoar.adapters.outbound.adapter.message;

import dev.ericms.quaoar.adapters.outbound.mapper.MessageOutboundMapper;
import dev.ericms.quaoar.adapters.outbound.repository.MessageRepository;
import dev.ericms.quaoar.adapters.outbound.repository.entity.MessageEntity;
import dev.ericms.quaoar.application.core.domain.Message;
import dev.ericms.quaoar.application.ports.outbound.message.FindMessageByIdOutboundPort;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class FindMessageByIdAdapter implements FindMessageByIdOutboundPort {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private MessageOutboundMapper messageOutboundMapper;


    @Override
    public Optional<Message> find(UUID messageId) {
        Optional<MessageEntity> messageEntity = messageRepository.findById(messageId);

        return messageEntity.map(message -> messageOutboundMapper.toDomain(message));
    }
}
