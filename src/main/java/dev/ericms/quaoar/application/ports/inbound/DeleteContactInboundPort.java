package dev.ericms.quaoar.application.ports.inbound;

import dev.ericms.quaoar.application.core.domain.Contact;

public interface DeleteContactInboundPort {

    void delete(Contact contact);

}
