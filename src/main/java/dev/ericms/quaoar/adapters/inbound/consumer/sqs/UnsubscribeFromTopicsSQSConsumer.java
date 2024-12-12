package dev.ericms.quaoar.adapters.inbound.consumer.sqs;

import com.amazon.sqs.javamessaging.message.SQSTextMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.ericms.quaoar.adapters.inbound.consumer.dto.SubscribeOrUnSubscribeToTopicPayload;
import dev.ericms.quaoar.adapters.inbound.consumer.mapper.SubscribeOrUnSubscribeToTopicMapper;
import dev.ericms.quaoar.application.core.events.UnsubscribeToTopicDomainEvent;
import dev.ericms.quaoar.application.core.exception.BusinessException;
import dev.ericms.quaoar.application.ports.outbound.events.EventPublisherOutboundPort;
import dev.ericms.quaoar.infrastructure.config.conditional.MessagingSqsCondition;
import jakarta.jms.JMSException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import static dev.ericms.quaoar.application.core.utils.Constants.INVALID_MESSAGE;
import static dev.ericms.quaoar.infrastructure.utils.Validation.isNull;

@Component
@Conditional(MessagingSqsCondition.class)
public class UnsubscribeFromTopicsSQSConsumer {

    private static final Logger logger = LoggerFactory.getLogger(UnsubscribeFromTopicsSQSConsumer.class);

    @Autowired
    private EventPublisherOutboundPort eventPublisherOutboundPort;

    @Autowired
    private SubscribeOrUnSubscribeToTopicMapper subscribeOrUnSubscribeToTopicMapper;

    @Autowired
    private ObjectMapper mapper;

    @JmsListener(destination = "${broker.consumer.queues.unsubscribe-topics}")
    public void handler(SQSTextMessage message) throws JMSException, JsonProcessingException {
        logger.info("Start processing message from payload -> {}", message);

        String content = message.getText();
        if (isNull(content)) {
            logger.error("Process stopped, invalid message founded");

            throw new BusinessException(INVALID_MESSAGE.getMessage());
        }

        SubscribeOrUnSubscribeToTopicPayload payload = mapper.readValue(content, SubscribeOrUnSubscribeToTopicPayload.class);

        eventPublisherOutboundPort.publishEvent(new UnsubscribeToTopicDomainEvent(subscribeOrUnSubscribeToTopicMapper.toDto(payload)));

        logger.info("Ended to processing message");
    }

}
