package dev.ericms.quaoar.adapters.outbound.adapter;

import dev.ericms.quaoar.adapters.outbound.mapper.ContactMapper;
import dev.ericms.quaoar.adapters.outbound.repository.ContactRepository;
import dev.ericms.quaoar.adapters.outbound.repository.entity.ContactEntity;
import dev.ericms.quaoar.application.core.domain.Contact;
import dev.ericms.quaoar.application.ports.outbound.FindContactByEmailOutboundPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class FindContactByEmailAdapter implements FindContactByEmailOutboundPort {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ContactMapper contactMapper;

    @Override
    public Optional<Contact> find(String email) {
        Optional<ContactEntity> contactEntity = contactRepository.findByEmail(email);

        return contactEntity.map(contact -> contactMapper.toDomain(contact));
    }
}
