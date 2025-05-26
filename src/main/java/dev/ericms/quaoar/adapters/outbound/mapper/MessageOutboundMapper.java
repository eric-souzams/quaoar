package dev.ericms.quaoar.adapters.outbound.mapper;

import dev.ericms.quaoar.adapters.outbound.repository.entity.MessageEntity;
import dev.ericms.quaoar.application.core.domain.Message;
import dev.ericms.quaoar.application.core.dto.PageResponseDTO;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface MessageOutboundMapper {

    MessageEntity toEntity(Message message);

    Message toDomain(MessageEntity messageEntity);

    default PageResponseDTO<Message> toPage(Page<MessageEntity> messageEntityPage) {
        PageResponseDTO<Message> pageResponseDTO = new PageResponseDTO<>();

        pageResponseDTO.setContent(messageEntityPage.map(this::toDomain).getContent());

        pageResponseDTO.setPageNumber(messageEntityPage.getNumber());
        pageResponseDTO.setPageSize(messageEntityPage.getSize());
        pageResponseDTO.setTotalElements(messageEntityPage.getTotalElements());
        pageResponseDTO.setTotalPages(messageEntityPage.getTotalPages());
        pageResponseDTO.setLast(messageEntityPage.isLast());

        return pageResponseDTO;
    }
}
