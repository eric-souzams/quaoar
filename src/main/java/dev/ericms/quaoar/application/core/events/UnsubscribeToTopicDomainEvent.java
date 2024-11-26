package dev.ericms.quaoar.application.core.events;

import dev.ericms.quaoar.application.core.dto.SubscribeOrUnSubscribeToTopicDto;
import dev.ericms.quaoar.application.core.events.interfaces.DomainEvent;

public class UnsubscribeToTopicDomainEvent implements DomainEvent {

    private final SubscribeOrUnSubscribeToTopicDto unsubscribeToTopicDto;

    public UnsubscribeToTopicDomainEvent(SubscribeOrUnSubscribeToTopicDto unsubscribeToTopicDto) {
        this.unsubscribeToTopicDto = unsubscribeToTopicDto;
    }

    public SubscribeOrUnSubscribeToTopicDto getBody() {
        return unsubscribeToTopicDto;
    }


}
