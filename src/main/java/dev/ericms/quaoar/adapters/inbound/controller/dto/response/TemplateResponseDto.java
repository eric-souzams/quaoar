package dev.ericms.quaoar.adapters.inbound.controller.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record TemplateResponseDto(
        UUID id,
        String name,
        String title,
        String content,
        Boolean active,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}