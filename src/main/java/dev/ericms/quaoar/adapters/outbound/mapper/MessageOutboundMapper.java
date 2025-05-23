package dev.ericms.quaoar.adapters.outbound.mapper;

import dev.ericms.quaoar.adapters.outbound.repository.entity.MessageEntity;
import dev.ericms.quaoar.application.core.domain.Message;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MessageOutboundMapper {

    MessageEntity toEntity(Message message);

    Message toDomain(MessageEntity messageEntity);

}
