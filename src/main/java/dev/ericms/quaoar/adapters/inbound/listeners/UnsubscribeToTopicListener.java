package dev.ericms.quaoar.adapters.inbound.listeners;

import dev.ericms.quaoar.application.core.dto.SubscribeOrUnSubscribeToTopicDto;
import dev.ericms.quaoar.application.core.exception.BusinessException;
import dev.ericms.quaoar.application.ports.inbound.topic.CheckIfExistsTopicInboundPort;
import dev.ericms.quaoar.application.ports.inbound.topic.UnsubscribeToTopicInbound;
import dev.ericms.quaoar.infrastructure.events.event.UnsubscribeToTopicEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import static dev.ericms.quaoar.application.core.utils.Constants.INVALID_OBJECT;

@Component
public class UnsubscribeToTopicListener {

    private static final Logger logger = LoggerFactory.getLogger(UnsubscribeToTopicListener.class);

    @Autowired
    private CheckIfExistsTopicInboundPort checkIfExistsTopicInboundPort;

    @Autowired
    private UnsubscribeToTopicInbound unsubscribeToTopicInbound;

    @EventListener
    public void handler(UnsubscribeToTopicEvent event) {
        logger.info("New event received: {}", event.getBody());

        try {
            SubscribeOrUnSubscribeToTopicDto payload = getPayload(event);
            for (String topicName : payload.getTopics()) {
                if (checkIfExistsTopicInboundPort.check(topicName)) {
                    //if exists, exclude contact from topic
                    logger.info("Unsubscribing contact {} from topic {}", payload.getEmail(), topicName);
                    unsubscribeToTopicInbound.unsubscribe(topicName, payload.getEmail());
                } else {
                    logger.error("Error on unsubscribe contact {} from topic {}, topic not exists", payload.getEmail(), topicName);
                }
            }
        } catch (BusinessException e) {
            logger.error("Error to process event: {}", e.getMessage());
        }
    }

    private SubscribeOrUnSubscribeToTopicDto getPayload(UnsubscribeToTopicEvent event) {
        if (event.getBody().getBody() != null) {
            return event.getBody().getBody();
        }

        throw new BusinessException(INVALID_OBJECT.getMessage());
    }

}
