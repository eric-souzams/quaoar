package dev.ericms.quaoar.adapters.outbound.repository;

import dev.ericms.quaoar.adapters.outbound.repository.entity.MessageEntity;
import dev.ericms.quaoar.adapters.outbound.repository.entity.MessageTopicEntity;
import dev.ericms.quaoar.adapters.outbound.repository.entity.TopicEntity;
import dev.ericms.quaoar.adapters.outbound.repository.entity.embed.MessageTopicId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface MessageTopicRepository extends JpaRepository<MessageTopicEntity, MessageTopicId> {

    List<MessageTopicEntity> findByIdMessageId(UUID messageId);

    List<MessageTopicEntity> findByIdTopicId(UUID topicId);

    @Query("SELECT t FROM TopicEntity t WHERE t.id IN " +
            "(SELECT mt.id.topicId FROM MessageTopicEntity mt WHERE mt.id.messageId = :messageId)")
    List<TopicEntity> findTopicsByMessageId(@Param("messageId") UUID messageId);

    @Query("SELECT m FROM MessageEntity m WHERE m.id IN " +
            "(SELECT mt.id.messageId FROM MessageTopicEntity mt WHERE mt.id.topicId = :topicId)")
    List<MessageEntity> findMessagesByTopicId(@Param("topicId") UUID topicId);

    void deleteByIdMessageIdAndIdTopicId(UUID messageId, UUID topicId);

}
