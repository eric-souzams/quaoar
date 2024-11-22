package dev.ericms.quaoar.adapters.inbound.consumer.rabbitmq;

import dev.ericms.quaoar.adapters.inbound.consumer.dto.SubscribeOrUnSubscribeToTopicPayload;
import dev.ericms.quaoar.application.ports.outbound.EventPublisherOutboundPort;
import dev.ericms.quaoar.infrastructure.config.conditional.MessagingRabbitMqCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

@Component
@Conditional(MessagingRabbitMqCondition.class)
public class SubscribeToTopicRabbitMQConsumer {

    private static final Logger logger = LoggerFactory.getLogger(SubscribeToTopicRabbitMQConsumer.class);

    @Autowired
    private EventPublisherOutboundPort eventPublisherOutboundPort;

    public void handler(SubscribeOrUnSubscribeToTopicPayload payload) {
        logger.info("Start processing message from payload -> {}", payload);


        logger.info("Ended to processing message");
    }

}
