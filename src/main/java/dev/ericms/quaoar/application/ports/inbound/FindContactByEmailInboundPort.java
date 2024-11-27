package dev.ericms.quaoar.application.ports.inbound;

import dev.ericms.quaoar.application.core.domain.Contact;

public interface FindContactByEmailInboundPort {

    Contact find(String email);

}
