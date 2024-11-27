package dev.ericms.quaoar.application.ports.outbound;

import dev.ericms.quaoar.application.core.domain.Contact;

public interface SaveContactOutboundPort {

    void save(Contact contact);

}
