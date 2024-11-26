package dev.ericms.quaoar.adapters.inbound.consumer.mapper;

import dev.ericms.quaoar.adapters.inbound.consumer.dto.ChangeUserInfoPayload;
import dev.ericms.quaoar.application.core.dto.ChangeUserInfoDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChangeUserInfoMapper {

    ChangeUserInfoDto toDto(ChangeUserInfoPayload payload);

}
