package dev.ericms.quaoar.infrastructure.config.rabbitmq;

import dev.ericms.quaoar.infrastructure.config.conditional.MessagingRabbitMqCondition;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
@Conditional(MessagingRabbitMqCondition.class)
public class RabbitMqConfig {

    private static final Logger logger = LoggerFactory.getLogger(RabbitMqConfig.class);

    @Autowired
    private AmqpAdmin amqpAdmin;

    @Value("${broker.consumer.exchange.name}")
    private String exchange;

    @Value("${broker.consumer.concurrent-consumers}")
    private Integer concurrentConsumers;

    @Value("${broker.consumer.prefetch-count}")
    private Integer prefetchCount;

    @Value("${broker.consumer.queues.delete-user}")
    private String queueDeleteUser;

    @Value("${broker.consumer.queues.change-user}")
    private String queueChangeUserInfo;

    @Value("${broker.consumer.queues.subscribe-topic}")
    private String queueSubscribeToTopic;

    @Value("${broker.consumer.queues.unsubscribe-topics}")
    private String queueUnsubscribeFromTopics;

    @Value("${broker.consumer.routing-keys.delete-user}")
    private String keyDeleteUser;

    @Value("${broker.consumer.routing-keys.change-user}")
    private String keyChangeUserInfo;

    @Value("${broker.consumer.routing-keys.subscribe-topic}")
    private String keySubscribeToTopic;

    @Value("${broker.consumer.routing-keys.unsubscribe-topics}")
    private String keyUnsubscribeFromTopics;

    private final RabbitMqProperties rabbitMqProperties;

    public RabbitMqConfig(RabbitMqProperties rabbitMqProperties) {
        this.rabbitMqProperties = rabbitMqProperties;
    }

    private Queue queue(String queue) {
        return new Queue(queue, true, false, false);
    }

    private TopicExchange topicExchange() {
        return new TopicExchange(exchange);
    }

    private Binding binding(TopicExchange topicExchange, Queue queue, String routingKey) {
        return BindingBuilder
                .bind(queue)
                .to(topicExchange)
                .with(routingKey);
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return template;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory listenerConfig(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        factory.setMessageConverter(messageConverter());
        factory.setPrefetchCount(prefetchCount);
        factory.setConcurrentConsumers(concurrentConsumers);
        return factory;
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        logger.info("Connecting to RabbitMQ...");

        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(rabbitMqProperties.getHost());
        connectionFactory.setPort(rabbitMqProperties.getPort());
        connectionFactory.setUsername(rabbitMqProperties.getUsername());
        connectionFactory.setPassword(rabbitMqProperties.getPassword());
        connectionFactory.setVirtualHost(rabbitMqProperties.getVirtualHost());
        connectionFactory.setConnectionLimit(rabbitMqProperties.getConnectionTimeout());
        return connectionFactory;
    }

    @Bean
    public AmqpAdmin amqpAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @PostConstruct
    @Conditional(MessagingRabbitMqCondition.class)
    public void startup() {
        TopicExchange topicExchange = topicExchange();

        Queue queueDeleteUser = queue(this.queueDeleteUser);
        Queue queueChangeUserInfo = queue(this.queueChangeUserInfo);
        Queue queueSubscribeToTopic = queue(this.queueSubscribeToTopic);
        Queue queueUnsubscribeFromTopics = queue(this.queueUnsubscribeFromTopics);

        Binding bindingDeleteUser = binding(topicExchange, queueDeleteUser, keyDeleteUser);
        Binding bindingChangeUserInfo = binding(topicExchange, queueChangeUserInfo, keyChangeUserInfo);
        Binding bindingSubscribeToTopic = binding(topicExchange, queueSubscribeToTopic, keySubscribeToTopic);
        Binding bindingUnsubscribeFromTopics = binding(topicExchange, queueUnsubscribeFromTopics, keyUnsubscribeFromTopics);

        logger.info("Creating queue '{}' if necessary", this.queueDeleteUser);
        amqpAdmin.declareQueue(queueDeleteUser);

        logger.info("Creating queue '{}' if necessary", this.queueChangeUserInfo);
        amqpAdmin.declareQueue(queueChangeUserInfo);

        logger.info("Creating queue '{}' if necessary", this.queueSubscribeToTopic);
        amqpAdmin.declareQueue(queueSubscribeToTopic);

        logger.info("Creating queue '{}' if necessary", this.queueUnsubscribeFromTopics);
        amqpAdmin.declareQueue(queueUnsubscribeFromTopics);

        logger.info("Creating topic exchange '{}' if necessary", this.exchange);
        amqpAdmin.declareExchange(topicExchange);

        logger.info("Declaring binding between queue '{}', topic '{}' and routing key '{}' if necessary", this.queueDeleteUser, this.exchange, this.keyDeleteUser);
        amqpAdmin.declareBinding(bindingDeleteUser);

        logger.info("Declaring binding between queue '{}', topic '{}' and routing key '{}' if necessary", this.queueChangeUserInfo, this.exchange, this.keyChangeUserInfo);
        amqpAdmin.declareBinding(bindingChangeUserInfo);

        logger.info("Declaring binding between queue '{}', topic '{}' and routing key '{}' if necessary", this.queueSubscribeToTopic, this.exchange, this.keySubscribeToTopic);
        amqpAdmin.declareBinding(bindingSubscribeToTopic);

        logger.info("Declaring binding between queue '{}', topic '{}' and routing key '{}' if necessary", this.queueUnsubscribeFromTopics, this.exchange, this.keyUnsubscribeFromTopics);
        amqpAdmin.declareBinding(bindingUnsubscribeFromTopics);
    }
}
