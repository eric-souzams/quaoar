package dev.ericms.quaoar.adapters.inbound.controller.mapper;

import dev.ericms.quaoar.adapters.inbound.controller.dto.request.CreateTemplateRequestDto;
import dev.ericms.quaoar.adapters.inbound.controller.dto.request.UpdateTemplateRequestDto;
import dev.ericms.quaoar.adapters.inbound.controller.dto.response.TemplateResponseDto;
import dev.ericms.quaoar.adapters.outbound.repository.entity.TemplateEntity;
import dev.ericms.quaoar.application.core.domain.Template;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TemplateInboundMapper {

    Template toDomain(TemplateEntity templateEntity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Template toDomain(CreateTemplateRequestDto createTemplateRequestDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Template toDomain(UpdateTemplateRequestDto updateTemplateRequestDto);

    TemplateEntity toEntity(Template template);

    TemplateResponseDto toResponseDto(Template template);

}
