package dev.ericms.quaoar.application.core.dto;

import java.util.Objects;

public class DeleteUserDto {

    private String email;

    private String integrationId;

    public DeleteUserDto() {
    }

    public DeleteUserDto(String email, String integrationId) {
        this.email = email;
        this.integrationId = integrationId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIntegrationId() {
        return integrationId;
    }

    public void setIntegrationId(String integrationId) {
        this.integrationId = integrationId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        DeleteUserDto that = (DeleteUserDto) o;
        return Objects.equals(email, that.email) && Objects.equals(integrationId, that.integrationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, integrationId);
    }

    @Override
    public String toString() {
        return "DeleteUserDto{" +
                "email='" + email + '\'' +
                ", integrationId='" + integrationId + '\'' +
                '}';
    }
}
