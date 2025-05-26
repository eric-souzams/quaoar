package dev.ericms.quaoar.adapters.outbound.adapter.message;

import dev.ericms.quaoar.adapters.outbound.mapper.MessageOutboundMapper;
import dev.ericms.quaoar.adapters.outbound.repository.MessageRepository;
import dev.ericms.quaoar.adapters.outbound.repository.entity.MessageEntity;
import dev.ericms.quaoar.application.core.domain.Message;
import dev.ericms.quaoar.application.core.dto.PageResponseDTO;
import dev.ericms.quaoar.application.core.enums.SortDirection;
import dev.ericms.quaoar.application.ports.outbound.message.FindAllMessagesOutboundPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class FindAllMessagesAdapter implements FindAllMessagesOutboundPort {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private MessageOutboundMapper messageOutboundMapper;

    @Override
    @Transactional(readOnly = true)
    public PageResponseDTO<Message> findAll(SortDirection sort, int page, int size) {
        Sort.Direction direction = (sort != null) ? Sort.Direction.valueOf(sort.name()) : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, "subject"));

        Page<MessageEntity> result = messageRepository.findAll(pageable);

        return messageOutboundMapper.toPage(result);
    }
}
