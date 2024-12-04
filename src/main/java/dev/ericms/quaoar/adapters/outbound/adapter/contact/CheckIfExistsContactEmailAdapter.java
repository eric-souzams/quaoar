package dev.ericms.quaoar.adapters.outbound.adapter.contact;

import dev.ericms.quaoar.adapters.outbound.repository.ContactRepository;
import dev.ericms.quaoar.application.ports.outbound.contact.CheckIfExistsContactEmailOutboundPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CheckIfExistsContactEmailAdapter implements CheckIfExistsContactEmailOutboundPort {

    @Autowired
    private ContactRepository contactRepository;

    @Transactional(readOnly = true)
    @Override
    public boolean check(String email) {
        return contactRepository.existsByEmail(email);
    }
}
