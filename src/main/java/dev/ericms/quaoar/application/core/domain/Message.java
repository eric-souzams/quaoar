package dev.ericms.quaoar.application.core.domain;

import dev.ericms.quaoar.adapters.outbound.repository.enums.MessageStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Message {

    private UUID id;

    private String subject;

    private String content;

    private String emailFrom;

    private String recipientsTo;

    private String recipientsCc;

    private String recipientsBcc;

    private Template template;

    private List<Topic> topics;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private MessageStatus status;

    private String messageId;

    public Message(UUID id, String subject, String content, String emailFrom, String recipientsTo,
                   String recipientsCc, String recipientsBcc, Template template, List<Topic> topics,
                   LocalDateTime createdAt, LocalDateTime updatedAt, MessageStatus status, String messageId) {
        this.id = id;
        this.subject = subject;
        this.content = content;
        this.emailFrom = emailFrom;
        this.recipientsTo = recipientsTo;
        this.recipientsCc = recipientsCc;
        this.recipientsBcc = recipientsBcc;
        this.template = template;
        this.topics = topics;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
        this.messageId = messageId;
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

    public Template getTemplate() {
        return template;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
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

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getRecipientsBcc() {
        return recipientsBcc;
    }

    public void setRecipientsBcc(String recipientsBcc) {
        this.recipientsBcc = recipientsBcc;
    }
}
