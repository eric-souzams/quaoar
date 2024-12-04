package dev.ericms.quaoar.adapters.outbound.repository;

import dev.ericms.quaoar.adapters.outbound.repository.entity.ContactEntity;
import dev.ericms.quaoar.adapters.outbound.repository.entity.TopicEntity;
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
    @Query("DELETE FROM ContactEntity c WHERE c.id = :contactId AND :topic IN (SELECT t FROM c.topics t WHERE t = :topic)")
    void removeTopicFromContact(@Param("contactId") UUID contactId, @Param("topic") TopicEntity topic);


}
