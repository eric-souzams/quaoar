package dev.ericms.quaoar.adapters.outbound.repository;

import dev.ericms.quaoar.adapters.outbound.repository.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ContactRepository extends JpaRepository<Contact, UUID> {
}
