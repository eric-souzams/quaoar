package dev.ericms.quaoar.application.core.dto;

import java.util.Objects;

public class ChangeUserInfoDto {

    private String name;

    private String email;

    private String integrationId;

    private Boolean blocked;

    public ChangeUserInfoDto() {
    }

    public ChangeUserInfoDto(String name, String email, String integrationId, Boolean blocked) {
        this.name = name;
        this.email = email;
        this.integrationId = integrationId;
        this.blocked = blocked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Boolean getBlocked() {
        return blocked;
    }

    public void setBlocked(Boolean blocked) {
        this.blocked = blocked;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ChangeUserInfoDto that = (ChangeUserInfoDto) o;
        return Objects.equals(name, that.name) && Objects.equals(email, that.email) && Objects.equals(integrationId, that.integrationId) && Objects.equals(blocked, that.blocked);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, integrationId, blocked);
    }

    @Override
    public String toString() {
        return "ChangeUserInfoDto{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", integrationId='" + integrationId + '\'' +
                ", blocked=" + blocked +
                '}';
    }
}
