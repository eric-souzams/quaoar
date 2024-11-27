package dev.ericms.quaoar.application.ports.outbound;

public interface CheckIfExistsContactEmailOutboundPort {

    boolean check(String email);

}
