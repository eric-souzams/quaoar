package dev.ericms.quaoar.adapters.outbound.adapter.message;

import dev.ericms.quaoar.adapters.outbound.repository.MessageTopicRepository;
import dev.ericms.quaoar.application.ports.outbound.message.DisassociateMessageToTopicOutboundPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
public class DisassociateMessageToTopicAdapter implements DisassociateMessageToTopicOutboundPort {

    @Autowired
    private MessageTopicRepository messageTopicRepository;

    @Override
    @Transactional
    public void disassociate(UUID messageId, UUID topicId) {
        messageTopicRepository.deleteByIdMessageIdAndIdTopicId(messageId, topicId);
    }
}
