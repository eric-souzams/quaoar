package dev.ericms.quaoar.application.ports.inbound.topic;

public interface UnsubscribeToTopicInbound {

    void unsubscribe(String topic, String email);

}
