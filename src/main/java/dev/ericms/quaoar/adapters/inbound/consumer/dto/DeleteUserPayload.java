package dev.ericms.quaoar.adapters.inbound.consumer.dto;

public record DeleteUserPayload(String name, String email, String integrationId) {
}
