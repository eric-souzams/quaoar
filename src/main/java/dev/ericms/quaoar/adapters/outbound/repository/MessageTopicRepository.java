package dev.ericms.quaoar.adapters.outbound.repository;

import dev.ericms.quaoar.adapters.outbound.repository.entity.MessageTopicEntity;
import dev.ericms.quaoar.adapters.outbound.repository.entity.embed.MessageTopicId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MessageTopicRepository extends JpaRepository<MessageTopicEntity, MessageTopicId> {

    List<MessageTopicEntity> findByIdMessageId(UUID messageId);

    List<MessageTopicEntity> findByIdTopicId(UUID topicId);

    void deleteByIdMessageIdAndIdTopicId(UUID messageId, UUID topicId);

}
