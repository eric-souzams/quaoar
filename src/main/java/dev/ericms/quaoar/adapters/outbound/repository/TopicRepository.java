package dev.ericms.quaoar.adapters.outbound.repository;

import dev.ericms.quaoar.adapters.outbound.repository.entity.TopicEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TopicRepository extends JpaRepository<TopicEntity, UUID> {

    Optional<TopicEntity> findByName(String topic);

    boolean existsByName(String topic);

    List<TopicEntity> findByNameIn(List<String> names);

}
