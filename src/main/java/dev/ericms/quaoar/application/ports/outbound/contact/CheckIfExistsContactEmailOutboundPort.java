package dev.ericms.quaoar.application.ports.outbound.contact;

public interface CheckIfExistsContactEmailOutboundPort {

    boolean check(String email);

}
