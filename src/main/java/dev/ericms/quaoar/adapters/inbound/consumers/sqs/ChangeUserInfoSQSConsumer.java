package dev.ericms.quaoar.adapters.inbound.consumers.sqs;

import dev.ericms.quaoar.adapters.inbound.consumers.dto.ChangeUserInfoPayload;
import dev.ericms.quaoar.adapters.inbound.consumers.mapper.ChangeUserInfoMapper;
import dev.ericms.quaoar.application.core.events.ChangeUserInfoDomainEvent;
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
public class ChangeUserInfoSQSConsumer {

    private static final Logger logger = LoggerFactory.getLogger(ChangeUserInfoSQSConsumer.class);

    @Autowired
    private EventPublisherOutboundPort eventPublisherOutboundPort;

    @Autowired
    private ChangeUserInfoMapper changeUserInfoMapper;

    @JmsListener(destination = "${broker.consumer.queues.change-user}")
    public void handler(ChangeUserInfoPayload payload) {
        logger.info("Start processing message from payload -> {}", payload);

        eventPublisherOutboundPort.publishEvent(new ChangeUserInfoDomainEvent(changeUserInfoMapper.toDto(payload)));

        logger.info("Ended to processing message");
    }

}
