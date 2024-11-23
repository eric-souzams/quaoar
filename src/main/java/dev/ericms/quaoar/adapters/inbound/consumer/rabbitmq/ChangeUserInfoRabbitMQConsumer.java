package dev.ericms.quaoar.adapters.inbound.consumer.rabbitmq;

import com.rabbitmq.client.Channel;
import dev.ericms.quaoar.adapters.inbound.consumer.dto.ChangeUserInfoPayload;
import dev.ericms.quaoar.application.ports.outbound.EventPublisherOutboundPort;
import dev.ericms.quaoar.infrastructure.config.conditional.MessagingRabbitMqCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@EnableRabbit
@Component
@Conditional(MessagingRabbitMqCondition.class)
public class ChangeUserInfoRabbitMQConsumer {

    private static final Logger logger = LoggerFactory.getLogger(ChangeUserInfoRabbitMQConsumer.class);

    @Autowired
    private EventPublisherOutboundPort eventPublisherOutboundPort;

    @RabbitListener(queues = {"${broker.consumer.queues.change-user}"}, containerFactory = "listenerConfig")
    public void handler(ChangeUserInfoPayload payload, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) {
        logger.info("Start processing message from payload -> {}", payload);


        logger.info("Ended to processing message");
    }

}
