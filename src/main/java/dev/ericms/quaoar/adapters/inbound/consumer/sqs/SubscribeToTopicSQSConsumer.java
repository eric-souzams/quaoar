package dev.ericms.quaoar.adapters.inbound.consumer.sqs;

import dev.ericms.quaoar.adapters.inbound.consumer.dto.SubscribeOrUnSubscribeToTopicPayload;
import dev.ericms.quaoar.application.ports.outbound.EventPublisherOutboundPort;
import dev.ericms.quaoar.infrastructure.config.conditional.MessagingAwsCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Conditional(MessagingAwsCondition.class)
public class SubscribeToTopicSQSConsumer {

    private static final Logger logger = LoggerFactory.getLogger(SubscribeToTopicSQSConsumer.class);

    @Autowired
    private EventPublisherOutboundPort eventPublisherOutboundPort;

    @JmsListener(destination = "${broker.consumer.queues.subscribe-topic}")
    public void handler(SubscribeOrUnSubscribeToTopicPayload payload) {
        logger.info("Start processing message from payload -> {}", payload);


        logger.info("Ended to processing message");
    }

}
