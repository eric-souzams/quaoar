package dev.ericms.quaoar.adapters.outbound.repository.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_contacts")
public class ContactEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", length = 150, nullable = false)
    private String name;

    @Column(name = "email", length = 150, nullable = false)
    private String email;

    @Column(name = "is_unsubscribed", nullable = false)
    private Boolean unsubscribed;

    @Column(name = "is_blocked", nullable = false)
    private Boolean blocked;

    @Column(name = "integration_id", nullable = true)
    private String integrationId;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @ManyToMany
    @JoinTable(
            name = "tb_contact_topics",
            joinColumns = @JoinColumn(name = "contact_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "topic_id", referencedColumnName = "id")
    )
    private List<TopicEntity> topics;

    public ContactEntity(UUID id, String name, String email, Boolean unsubscribed, Boolean blocked,
                         String integrationId, LocalDateTime createdAt, LocalDateTime updatedAt, List<TopicEntity> topics) {
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

    public ContactEntity() {
    }

    @PreUpdate
    private void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    @PrePersist
    private void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
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

    public List<TopicEntity> getTopics() {
        return topics;
    }

    public void addTopics(TopicEntity topic) {
        this.topics.add(topic);
    }

}
