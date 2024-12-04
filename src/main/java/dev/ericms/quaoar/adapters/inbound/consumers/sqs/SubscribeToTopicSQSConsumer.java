package dev.ericms.quaoar.adapters.inbound.consumers.sqs;

import dev.ericms.quaoar.adapters.inbound.consumers.dto.SubscribeOrUnSubscribeToTopicPayload;
import dev.ericms.quaoar.adapters.inbound.consumers.mapper.SubscribeOrUnSubscribeToTopicMapper;
import dev.ericms.quaoar.application.core.events.SubscribeToTopicDomainEvent;
import dev.ericms.quaoar.application.ports.outbound.events.EventPublisherOutboundPort;
import dev.ericms.quaoar.infrastructure.config.conditional.MessagingSqsCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Conditional(MessagingSqsCondition.class)
public class SubscribeToTopicSQSConsumer {

    private static final Logger logger = LoggerFactory.getLogger(SubscribeToTopicSQSConsumer.class);

    @Autowired
    private EventPublisherOutboundPort eventPublisherOutboundPort;

    @Autowired
    private SubscribeOrUnSubscribeToTopicMapper subscribeOrUnSubscribeToTopicMapper;

    @JmsListener(destination = "${broker.consumer.queues.subscribe-topic}")
    public void handler(SubscribeOrUnSubscribeToTopicPayload payload) {
        logger.info("Start processing message from payload -> {}", payload);

        eventPublisherOutboundPort.publishEvent(new SubscribeToTopicDomainEvent(subscribeOrUnSubscribeToTopicMapper.toDto(payload)));

        logger.info("Ended to processing message");
    }

}
