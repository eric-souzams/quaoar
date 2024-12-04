package dev.ericms.quaoar.adapters.inbound.consumers.dto;

public record ChangeUserInfoPayload(String name, String email, String integrationId, Boolean blocked) {
}
