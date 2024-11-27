package dev.ericms.quaoar.adapters.outbound.adapter;

import dev.ericms.quaoar.adapters.outbound.repository.ContactRepository;
import dev.ericms.quaoar.application.ports.outbound.CheckIfExistsContactEmailOutboundPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CheckIfExistsContactEmailAdapter implements CheckIfExistsContactEmailOutboundPort {

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public boolean check(String email) {
        return contactRepository.existsByEmail(email);
    }
}
