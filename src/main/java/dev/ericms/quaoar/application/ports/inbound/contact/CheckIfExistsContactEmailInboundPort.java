package dev.ericms.quaoar.application.ports.inbound.contact;

public interface CheckIfExistsContactEmailInboundPort {

    boolean check(String email);

}
