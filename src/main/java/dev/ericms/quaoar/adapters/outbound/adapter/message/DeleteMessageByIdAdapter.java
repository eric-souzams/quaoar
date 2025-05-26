package dev.ericms.quaoar.adapters.outbound.adapter.message;

import dev.ericms.quaoar.adapters.outbound.mapper.MessageOutboundMapper;
import dev.ericms.quaoar.adapters.outbound.repository.MessageRepository;
import dev.ericms.quaoar.adapters.outbound.repository.entity.MessageEntity;
import dev.ericms.quaoar.application.core.domain.Message;
import dev.ericms.quaoar.application.ports.outbound.message.DeleteMessageOutboundPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DeleteMessageByIdAdapter implements DeleteMessageOutboundPort {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private MessageOutboundMapper messageOutboundMapper;

    @Override
    @Transactional
    public void delete(Message message) {
        MessageEntity messageEntity = messageOutboundMapper.toEntity(message);

        //related topics will be deleted with cascade delete
        messageRepository.delete(messageEntity);
    }
}
