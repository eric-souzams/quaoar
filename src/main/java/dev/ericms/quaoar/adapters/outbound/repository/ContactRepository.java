package dev.ericms.quaoar.adapters.outbound.repository;

import dev.ericms.quaoar.adapters.outbound.repository.entity.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface ContactRepository extends JpaRepository<ContactEntity, UUID> {

    Optional<ContactEntity> findByEmail(String email);

    boolean existsByEmail(String email);

    @Modifying
    @Query(value = "DELETE FROM tb_contact_topics WHERE contact_id = :contactId AND topic_id = :topicId", nativeQuery = true)
    void removeTopicFromContact(@Param("contactId") UUID contactId, @Param("topicId") UUID topicId);

}
