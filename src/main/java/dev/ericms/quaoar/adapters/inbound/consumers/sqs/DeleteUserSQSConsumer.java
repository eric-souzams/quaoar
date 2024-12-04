package dev.ericms.quaoar.adapters.inbound.consumers.sqs;

import dev.ericms.quaoar.adapters.inbound.consumers.dto.DeleteUserPayload;
import dev.ericms.quaoar.adapters.inbound.consumers.mapper.DeleteUserMapper;
import dev.ericms.quaoar.application.core.events.DeleteUserDomainEvent;
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
public class DeleteUserSQSConsumer {

    private static final Logger logger = LoggerFactory.getLogger(DeleteUserSQSConsumer.class);

    @Autowired
    private EventPublisherOutboundPort eventPublisherOutboundPort;

    @Autowired
    private DeleteUserMapper deleteUserMapper;

    @JmsListener(destination = "${broker.consumer.queues.delete-user}")
    public void handler(DeleteUserPayload payload) {
        logger.info("Start processing message from payload -> {}", payload);

        eventPublisherOutboundPort.publishEvent(new DeleteUserDomainEvent(deleteUserMapper.toDto(payload)));

        logger.info("Ended to processing message");
    }

}
