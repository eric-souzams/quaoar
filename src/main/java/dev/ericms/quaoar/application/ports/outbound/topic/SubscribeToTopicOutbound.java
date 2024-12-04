package dev.ericms.quaoar.application.ports.outbound.topic;

public interface SubscribeToTopicOutbound {

    void subscribe(String topic, String email);

}
