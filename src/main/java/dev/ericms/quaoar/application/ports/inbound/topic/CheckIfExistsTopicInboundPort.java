package dev.ericms.quaoar.application.ports.inbound.topic;

public interface CheckIfExistsTopicInboundPort {

    boolean check(String topic);

}
