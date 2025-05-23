package dev.ericms.quaoar.adapters.outbound.repository.entity;

import dev.ericms.quaoar.adapters.outbound.repository.entity.embed.MessageTopicId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "tb_message_topic")
public class MessageTopicEntity {

    @EmbeddedId
    private MessageTopicId id = new MessageTopicId();

    public MessageTopicEntity() {
    }

    public MessageTopicEntity(UUID messageId, UUID topicId) {
        this.id = new MessageTopicId(messageId, topicId);
    }

    public MessageTopicId getId() {
        return id;
    }

    public void setId(MessageTopicId id) {
        this.id = id;
    }
}
