package dev.ericms.quaoar.adapters.outbound.repository;

import dev.ericms.quaoar.adapters.outbound.repository.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TopicRepository extends JpaRepository<Topic, UUID> {
}
