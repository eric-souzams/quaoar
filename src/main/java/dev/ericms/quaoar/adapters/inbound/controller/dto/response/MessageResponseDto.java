package dev.ericms.quaoar.adapters.inbound.controller.dto.response;

import dev.ericms.quaoar.adapters.outbound.repository.enums.MessageStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record MessageResponseDto(
        UUID id,
        String subject,
        String content,
        String emailFrom,
        List<String> recipientsTo,
        List<String> recipientsCc,
        List<String> recipientsBcc,
        TemplateResponse template,
        List<TopicResponse> topics,
        MessageStatus status,
        String messageId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

    public record TemplateResponse(
            UUID id,
            String name
    ) {
    }

    public record TopicResponse(
            UUID id,
            String name
    ) {
    }
}
