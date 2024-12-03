package dev.ericms.quaoar.application.ports.outbound;

import dev.ericms.quaoar.application.core.domain.Contact;

public interface DeleteContactOutboundPort {

    void delete(Contact contact);

}
