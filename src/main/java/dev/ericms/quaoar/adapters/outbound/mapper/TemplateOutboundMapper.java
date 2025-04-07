package dev.ericms.quaoar.adapters.outbound.mapper;

import dev.ericms.quaoar.adapters.inbound.controller.dto.request.CreateTemplateRequestDto;
import dev.ericms.quaoar.adapters.inbound.controller.dto.request.UpdateTemplateRequestDto;
import dev.ericms.quaoar.adapters.inbound.controller.dto.response.TemplateResponseDto;
import dev.ericms.quaoar.adapters.outbound.repository.entity.TemplateEntity;
import dev.ericms.quaoar.application.core.domain.Template;
import dev.ericms.quaoar.application.core.dto.PageResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface TemplateOutboundMapper {

    Template toDomain(TemplateEntity templateEntity);

    TemplateEntity toEntity(Template template);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Template toDomain(CreateTemplateRequestDto createTemplateRequestDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Template toDomain(UpdateTemplateRequestDto updateTemplateRequestDto);

    TemplateResponseDto toResponseDto(Template template);

    default PageResponseDTO<Template> toPage(Page<TemplateEntity> templateEntityPage) {
        PageResponseDTO<Template> pageResponseDTO = new PageResponseDTO<>();

        pageResponseDTO.setContent(templateEntityPage.map(this::toDomain).getContent());

        pageResponseDTO.setPageNumber(templateEntityPage.getNumber());
        pageResponseDTO.setPageSize(templateEntityPage.getSize());
        pageResponseDTO.setTotalElements(templateEntityPage.getTotalElements());
        pageResponseDTO.setTotalPages(templateEntityPage.getTotalPages());
        pageResponseDTO.setLast(templateEntityPage.isLast());

        return pageResponseDTO;
    }
}
