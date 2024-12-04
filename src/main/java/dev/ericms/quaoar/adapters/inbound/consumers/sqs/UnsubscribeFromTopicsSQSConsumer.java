package dev.ericms.quaoar.adapters.inbound.consumers.sqs;

import dev.ericms.quaoar.adapters.inbound.consumers.dto.SubscribeOrUnSubscribeToTopicPayload;
import dev.ericms.quaoar.adapters.inbound.consumers.mapper.SubscribeOrUnSubscribeToTopicMapper;
import dev.ericms.quaoar.application.core.events.UnsubscribeToTopicDomainEvent;
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
public class UnsubscribeFromTopicsSQSConsumer {

    private static final Logger logger = LoggerFactory.getLogger(UnsubscribeFromTopicsSQSConsumer.class);

    @Autowired
    private EventPublisherOutboundPort eventPublisherOutboundPort;

    @Autowired
    private SubscribeOrUnSubscribeToTopicMapper subscribeOrUnSubscribeToTopicMapper;

    @JmsListener(destination = "${broker.consumer.queues.unsubscribe-topics}")
    public void handler(SubscribeOrUnSubscribeToTopicPayload payload) {
        logger.info("Start processing message from payload -> {}", payload);

        eventPublisherOutboundPort.publishEvent(new UnsubscribeToTopicDomainEvent(subscribeOrUnSubscribeToTopicMapper.toDto(payload)));

        logger.info("Ended to processing message");
    }

}
