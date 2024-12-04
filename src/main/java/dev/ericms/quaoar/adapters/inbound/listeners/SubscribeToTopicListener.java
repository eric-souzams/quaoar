package dev.ericms.quaoar.adapters.inbound.listeners;

import dev.ericms.quaoar.application.core.domain.Topic;
import dev.ericms.quaoar.application.core.dto.SubscribeOrUnSubscribeToTopicDto;
import dev.ericms.quaoar.application.core.exception.BusinessException;
import dev.ericms.quaoar.application.ports.inbound.topic.CheckIfExistsTopicInboundPort;
import dev.ericms.quaoar.application.ports.inbound.topic.SaveTopicInboundPort;
import dev.ericms.quaoar.application.ports.inbound.topic.SubscribeToTopicInbound;
import dev.ericms.quaoar.infrastructure.events.event.SubscribeToTopicEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import static dev.ericms.quaoar.application.core.utils.Constants.INVALID_OBJECT;

@Component
public class SubscribeToTopicListener {

    private static final Logger logger = LoggerFactory.getLogger(SubscribeToTopicListener.class);

    @Autowired
    private SaveTopicInboundPort saveTopicInboundPort;

    @Autowired
    private CheckIfExistsTopicInboundPort checkIfExistsTopicInboundPort;

    @Autowired
    private SubscribeToTopicInbound subscribeToTopicInbound;

    @EventListener
    public void handler(SubscribeToTopicEvent event) {
        logger.info("New event received: {}", event.getBody());

        try {
            SubscribeOrUnSubscribeToTopicDto payload = getPayload(event);
            for (String topicName : payload.getTopics()) {
                if (checkIfExistsTopicInboundPort.check(topicName)) {
                    //if exists, include in topic
                    subscribeToTopicInbound.subscribe(topicName, payload.getEmail());
                } else {
                    //if not exists, create topic and include in topic
                    Topic topic = saveTopicInboundPort.save(createTopicBuilder(topicName, new Topic()));
                    subscribeToTopicInbound.subscribe(topic.getName(), payload.getEmail());
                }
            }
        } catch (BusinessException e) {
            logger.error("Error to process event: {}", e.getMessage());
        }
    }

    private SubscribeOrUnSubscribeToTopicDto getPayload(SubscribeToTopicEvent event) {
        if (event.getBody().getBody() != null) {
            return event.getBody().getBody();
        }

        throw new BusinessException(INVALID_OBJECT.getMessage());
    }

    private Topic createTopicBuilder(String topicName, Topic topic) {
        topic.setName(topicName);
        topic.setActive(true);

        return topic;
    }

}
