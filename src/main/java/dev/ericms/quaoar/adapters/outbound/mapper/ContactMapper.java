package dev.ericms.quaoar.adapters.outbound.mapper;

import dev.ericms.quaoar.adapters.outbound.repository.entity.ContactEntity;
import dev.ericms.quaoar.application.core.domain.Contact;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContactMapper {

    ContactEntity toEntity(Contact contact);

    Contact toDomain(ContactEntity contactEntity);

}
