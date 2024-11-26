package dev.ericms.quaoar.adapters.inbound.consumer.sqs;

import dev.ericms.quaoar.adapters.inbound.consumer.dto.ChangeUserInfoPayload;
import dev.ericms.quaoar.application.ports.outbound.EventPublisherOutboundPort;
import dev.ericms.quaoar.infrastructure.config.conditional.MessagingSqsCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Conditional(MessagingSqsCondition.class)
public class ChangeUserInfoSQSConsumer {

    private static final Logger logger = LoggerFactory.getLogger(ChangeUserInfoSQSConsumer.class);

    @Autowired
    private EventPublisherOutboundPort eventPublisherOutboundPort;

    @JmsListener(destination = "${broker.consumer.queues.change-user}")
    public void handler(ChangeUserInfoPayload payload) {
        logger.info("Start processing message from payload -> {}", payload);


        logger.info("Ended to processing message");
    }

}
