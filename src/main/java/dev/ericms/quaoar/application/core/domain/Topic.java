package dev.ericms.quaoar.application.core.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Topic {

    private UUID id;

    private String title;

    private Boolean active;

    private String integrationId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private List<Contact> contacts;

    public Topic(UUID id, String title, Boolean active, String integrationId, LocalDateTime createdAt,
                 LocalDateTime updatedAt, List<Contact> contacts) {
        this.id = id;
        this.title = title;
        this.active = active;
        this.integrationId = integrationId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.contacts = contacts;
    }

    public Topic() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getIntegrationId() {
        return integrationId;
    }

    public void setIntegrationId(String integrationId) {
        this.integrationId = integrationId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }
}
