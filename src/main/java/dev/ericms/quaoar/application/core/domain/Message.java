package dev.ericms.quaoar.application.core.domain;

import dev.ericms.quaoar.adapters.outbound.repository.enums.MessageStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public class Message {

    private UUID id;

    private String subject;

    private String content;

    private String recipients;

    private Template template;

    private Topic topic;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private MessageStatus status;

    public Message(UUID id, String subject, String content, String recipients, Template template,
                   Topic topic, LocalDateTime createdAt, LocalDateTime updatedAt, MessageStatus status) {
        this.id = id;
        this.subject = subject;
        this.content = content;
        this.recipients = recipients;
        this.template = template;
        this.topic = topic;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
    }

    public Message() {
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

    public String getRecipients() {
        return recipients;
    }

    public void setRecipients(String recipients) {
        this.recipients = recipients;
    }

    public Template getTemplate() {
        return template;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
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

}
