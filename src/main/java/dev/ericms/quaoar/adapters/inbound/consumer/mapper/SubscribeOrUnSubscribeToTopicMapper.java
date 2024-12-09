package dev.ericms.quaoar.adapters.inbound.consumer.mapper;

import dev.ericms.quaoar.adapters.inbound.consumer.dto.SubscribeOrUnSubscribeToTopicPayload;
import dev.ericms.quaoar.application.core.dto.SubscribeOrUnSubscribeToTopicDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubscribeOrUnSubscribeToTopicMapper {

    SubscribeOrUnSubscribeToTopicDto toDto(SubscribeOrUnSubscribeToTopicPayload payload);

}
