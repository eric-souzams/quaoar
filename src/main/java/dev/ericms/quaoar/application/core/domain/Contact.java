package dev.ericms.quaoar.application.core.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Contact {

    private UUID id;

    private String name;

    private String email;

    private Boolean unsubscribed;

    private Boolean blocked;

    private String integrationId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private List<AbstractTopic> topics;

    public Contact(UUID id, String name, String email, Boolean unsubscribed, Boolean blocked,
                   String integrationId, LocalDateTime createdAt, LocalDateTime updatedAt, List<AbstractTopic> topics) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.unsubscribed = unsubscribed;
        this.blocked = blocked;
        this.integrationId = integrationId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.topics = topics;
    }

    public Contact() {
        this.unsubscribed = true;
        this.topics = new ArrayList<>();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public Boolean getUnsubscribed() {
        return unsubscribed;
    }

    public void setUnsubscribed(Boolean unsubscribed) {
        this.unsubscribed = unsubscribed;
    }

    public Boolean getBlocked() {
        return blocked;
    }

    public void setBlocked(Boolean blocked) {
        this.blocked = blocked;
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

    public List<AbstractTopic> getTopics() {
        return topics;
    }

    public void setTopics(List<AbstractTopic> topics) {
        this.topics = topics;
    }
}
