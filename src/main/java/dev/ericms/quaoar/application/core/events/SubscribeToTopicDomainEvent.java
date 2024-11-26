package dev.ericms.quaoar.application.core.events;

import dev.ericms.quaoar.application.core.dto.SubscribeOrUnSubscribeToTopicDto;
import dev.ericms.quaoar.application.core.events.interfaces.DomainEvent;

public class SubscribeToTopicDomainEvent implements DomainEvent {

    private final SubscribeOrUnSubscribeToTopicDto subscribeToTopicDto;

    public SubscribeToTopicDomainEvent(SubscribeOrUnSubscribeToTopicDto subscribeToTopicDto) {
        this.subscribeToTopicDto = subscribeToTopicDto;
    }

    public SubscribeOrUnSubscribeToTopicDto getBody() {
        return subscribeToTopicDto;
    }


}
