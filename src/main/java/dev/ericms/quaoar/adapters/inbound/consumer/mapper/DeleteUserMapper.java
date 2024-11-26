package dev.ericms.quaoar.adapters.inbound.consumer.mapper;

import dev.ericms.quaoar.adapters.inbound.consumer.dto.DeleteUserPayload;
import dev.ericms.quaoar.application.core.dto.DeleteUserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DeleteUserMapper {

    DeleteUserDto toDto(DeleteUserPayload payload);

}
