package dev.ericms.quaoar.application.ports.outbound.contact;

import dev.ericms.quaoar.application.core.domain.Contact;

import java.util.Optional;

public interface FindContactByEmailOutboundPort {

    Optional<Contact> find(String email);

}
