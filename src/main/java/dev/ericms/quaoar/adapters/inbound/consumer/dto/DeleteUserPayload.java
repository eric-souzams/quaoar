package dev.ericms.quaoar.adapters.inbound.consumer.dto;

public record DeleteUserPayload(String email, String integrationId) {
}
