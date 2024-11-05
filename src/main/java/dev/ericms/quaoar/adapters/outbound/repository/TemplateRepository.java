package dev.ericms.quaoar.adapters.outbound.repository;

import dev.ericms.quaoar.adapters.outbound.repository.entity.Template;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TemplateRepository extends JpaRepository<Template, UUID> {
}
