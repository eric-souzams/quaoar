package dev.ericms.quaoar.adapters.inbound.consumers.mapper;

import dev.ericms.quaoar.adapters.inbound.consumers.dto.SubscribeOrUnSubscribeToTopicPayload;
import dev.ericms.quaoar.application.core.dto.SubscribeOrUnSubscribeToTopicDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubscribeOrUnSubscribeToTopicMapper {

    SubscribeOrUnSubscribeToTopicDto toDto(SubscribeOrUnSubscribeToTopicPayload payload);

}
