package dev.ericms.quaoar.adapters.outbound.adapter.contact;

import dev.ericms.quaoar.adapters.outbound.mapper.ContactOutboundMapper;
import dev.ericms.quaoar.adapters.outbound.repository.ContactRepository;
import dev.ericms.quaoar.adapters.outbound.repository.entity.ContactEntity;
import dev.ericms.quaoar.application.core.domain.Contact;
import dev.ericms.quaoar.application.ports.outbound.contact.DeleteContactOutboundPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DeleteContactAdapter implements DeleteContactOutboundPort {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ContactOutboundMapper contactOutboundMapper;

    @Override
    @Transactional
    public void delete(Contact contact) {
        ContactEntity contactEntity = contactOutboundMapper.toEntity(contact);

        contactRepository.delete(contactEntity);
    }
}
