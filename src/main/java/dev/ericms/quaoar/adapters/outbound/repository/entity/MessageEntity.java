package dev.ericms.quaoar.adapters.outbound.repository.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import dev.ericms.quaoar.adapters.outbound.repository.enums.MessageStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_messages")
public class MessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "subject", nullable = false, length = 255)
    private String subject;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "email_from", nullable = false)
    private String emailFrom;

    @Column(name = "recipients_to", nullable = false)
    private String recipientsTo;

    @Column(name = "recipients_cc", nullable = false)
    private String recipientsCc;

    @ManyToOne
    @JoinColumn(name = "template_id", referencedColumnName = "id")
    private TemplateEntity template;

    @ManyToMany
    @JoinTable(
            name = "tb_message_topics",
            joinColumns = @JoinColumn(name = "message_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "topic_id", referencedColumnName = "id")
    )
    private List<TopicEntity> topics;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private MessageStatus status;

    public MessageEntity(UUID id, String subject, String content, String emailFrom, String recipientsTo,
                         String recipientsCc, TemplateEntity template, List<TopicEntity> topics,
                         LocalDateTime createdAt, LocalDateTime updatedAt, MessageStatus status) {
        this.id = id;
        this.subject = subject;
        this.content = content;
        this.emailFrom = emailFrom;
        this.recipientsTo = recipientsTo;
        this.recipientsCc = recipientsCc;
        this.template = template;
        this.topics = topics;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
    }

    public MessageEntity() {
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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public TemplateEntity getTemplate() {
        return template;
    }

    public void setTemplate(TemplateEntity template) {
        this.template = template;
    }

    public List<TopicEntity> getTopics() {
        return topics;
    }

    public void setTopics(List<TopicEntity> topics) {
        this.topics = topics;
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

    public MessageStatus getStatus() {
        return status;
    }

    public void setStatus(MessageStatus status) {
        this.status = status;
    }

    public String getEmailFrom() {
        return emailFrom;
    }

    public void setEmailFrom(String emailFrom) {
        this.emailFrom = emailFrom;
    }

    public String getRecipientsTo() {
        return recipientsTo;
    }

    public void setRecipientsTo(String recipientsTo) {
        this.recipientsTo = recipientsTo;
    }

    public String getRecipientsCc() {
        return recipientsCc;
    }

    public void setRecipientsCc(String recipientsCc) {
        this.recipientsCc = recipientsCc;
    }
}
