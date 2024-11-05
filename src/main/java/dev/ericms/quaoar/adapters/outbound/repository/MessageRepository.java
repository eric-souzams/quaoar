package dev.ericms.quaoar.adapters.outbound.repository;

import dev.ericms.quaoar.adapters.outbound.repository.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MessageRepository extends JpaRepository<Message, UUID> {
}
