package dev.ericms.quaoar.adapters.inbound.consumer.rabbitmq;

import com.rabbitmq.client.Channel;
import dev.ericms.quaoar.adapters.inbound.consumer.dto.DeleteUserPayload;
import dev.ericms.quaoar.adapters.inbound.consumer.mapper.DeleteUserMapper;
import dev.ericms.quaoar.application.core.events.DeleteUserDomainEvent;
import dev.ericms.quaoar.application.ports.outbound.EventPublisherOutboundPort;
import dev.ericms.quaoar.infrastructure.config.conditional.MessagingRabbitMqCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Conditional(MessagingRabbitMqCondition.class)
public class DeleteUserRabbitMQConsumer {

    private static final Logger logger = LoggerFactory.getLogger(DeleteUserRabbitMQConsumer.class);

    @Autowired
    private EventPublisherOutboundPort eventPublisherOutboundPort;

    @Autowired
    private DeleteUserMapper deleteUserMapper;

    @RabbitListener(queues = {"${broker.consumer.queues.delete-user}"}, containerFactory = "listenerConfig")
    public void handler(DeleteUserPayload payload, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
        logger.info("Start processing message from payload -> {}", payload);

        eventPublisherOutboundPort.publishEvent(new DeleteUserDomainEvent(deleteUserMapper.toDto(payload)));

        channel.basicAck(tag, false);

        logger.info("Ended to processing message");
    }

}
