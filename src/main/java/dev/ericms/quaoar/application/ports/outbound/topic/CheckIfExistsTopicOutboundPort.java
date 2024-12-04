package dev.ericms.quaoar.application.ports.outbound.topic;

public interface CheckIfExistsTopicOutboundPort {

    boolean check(String topic);

}
