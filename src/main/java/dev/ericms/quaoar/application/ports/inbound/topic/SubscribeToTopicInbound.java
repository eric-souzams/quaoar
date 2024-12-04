package dev.ericms.quaoar.application.ports.inbound.topic;

public interface SubscribeToTopicInbound {

    void subscribe(String topic, String email);


}
