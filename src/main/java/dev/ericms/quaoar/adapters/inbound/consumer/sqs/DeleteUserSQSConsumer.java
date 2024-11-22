package dev.ericms.quaoar.adapters.inbound.consumer.sqs;

import dev.ericms.quaoar.adapters.inbound.consumer.dto.DeleteUserPayload;
import dev.ericms.quaoar.application.ports.outbound.EventPublisherOutboundPort;
import dev.ericms.quaoar.infrastructure.config.conditional.MessagingAwsCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

@Component
@Conditional(MessagingAwsCondition.class)
public class DeleteUserSQSConsumer {

    private static final Logger logger = LoggerFactory.getLogger(DeleteUserSQSConsumer.class);

    @Autowired
    private EventPublisherOutboundPort eventPublisherOutboundPort;

    public void handler(DeleteUserPayload payload) {
        logger.info("Start processing message from payload -> {}", payload);


        logger.info("Ended to processing message");
    }

}
