package dev.ericms.quaoar.adapters.outbound.repository.entity.embed;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Objects;
import java.util.UUID;

@Embeddable
public class MessageTopicId {

    @Column(name = "message_id")
    private UUID messageId;

    @Column(name = "topic_id")
    private UUID topicId;

    public MessageTopicId() {}

    public MessageTopicId(UUID messageId, UUID topicId) {
        this.messageId = messageId;
        this.topicId = topicId;
    }

    public UUID getMessageId() {
        return messageId;
    }

    public void setMessageId(UUID messageId) {
        this.messageId = messageId;
    }

    public UUID getTopicId() {
        return topicId;
    }

    public void setTopicId(UUID topicId) {
        this.topicId = topicId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MessageTopicId that = (MessageTopicId) o;
        return Objects.equals(messageId, that.messageId) && Objects.equals(topicId, that.topicId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(messageId, topicId);
    }
}
