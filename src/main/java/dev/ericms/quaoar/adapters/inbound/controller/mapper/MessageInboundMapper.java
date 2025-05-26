package dev.ericms.quaoar.adapters.inbound.controller.mapper;

import dev.ericms.quaoar.adapters.inbound.controller.dto.response.MessageResponseDto;
import dev.ericms.quaoar.application.core.domain.Message;
import dev.ericms.quaoar.application.core.dto.PageResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MessageInboundMapper {

    @Mapping(target = "recipientsTo", expression = "java(dev.ericms.quaoar.infrastructure.utils.JsonUtils.stringToList(message.getRecipientsTo()))")
    @Mapping(target = "recipientsCc", expression = "java(dev.ericms.quaoar.infrastructure.utils.JsonUtils.stringToList(message.getRecipientsCc()))")
    @Mapping(target = "recipientsBcc", expression = "java(dev.ericms.quaoar.infrastructure.utils.JsonUtils.stringToList(message.getRecipientsBcc()))")
    MessageResponseDto toResponseDto(Message message);

    PageResponseDTO<MessageResponseDto> toPage(PageResponseDTO<Message> messages);

}
