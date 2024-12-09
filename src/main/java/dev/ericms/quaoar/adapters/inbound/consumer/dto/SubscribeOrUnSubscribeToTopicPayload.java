package dev.ericms.quaoar.adapters.inbound.consumer.dto;

import java.util.List;

public record SubscribeOrUnSubscribeToTopicPayload(String email, String integrationId, List<String> topics) {
}
