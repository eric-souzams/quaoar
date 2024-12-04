package dev.ericms.quaoar.adapters.inbound.consumers.dto;

import java.util.List;

public record SubscribeOrUnSubscribeToTopicPayload(String email, String integrationId, List<String> topics) {
}
