package dev.ericms.quaoar.application.ports.outbound.topic;

public interface UnsubscribeToTopicOutbound {

    void unsubscribe(String topic, String email);

}
