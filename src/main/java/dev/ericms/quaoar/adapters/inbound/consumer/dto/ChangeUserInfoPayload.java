package dev.ericms.quaoar.adapters.inbound.consumer.dto;

public record ChangeUserInfoPayload(String name, String email, String integrationId, Boolean blocked) {
}
