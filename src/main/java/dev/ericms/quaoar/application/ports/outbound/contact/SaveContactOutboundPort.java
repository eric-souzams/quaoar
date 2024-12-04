package dev.ericms.quaoar.application.ports.outbound.contact;

import dev.ericms.quaoar.application.core.domain.Contact;

public interface SaveContactOutboundPort {

    void save(Contact contact);

}
