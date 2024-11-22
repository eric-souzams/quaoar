package dev.ericms.quaoar.infrastructure.config.rabbitmq;

import dev.ericms.quaoar.infrastructure.config.conditional.MessagingRabbitMqCondition;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

@Component
@Conditional(MessagingRabbitMqCondition.class)
public class RabbitMqProperties {

    @Value("${messaging.rabbitmq.host}")
    private String host;

    @Value("${messaging.rabbitmq.port}")
    private Integer port;

    @Value("${messaging.rabbitmq.username}")
    private String username;

    @Value("${messaging.rabbitmq.password}")
    private String password;

    @Value("${messaging.rabbitmq.virtual-host}")
    private String virtualHost;

    @Value("${messaging.rabbitmq.connection-timeout}")
    private Integer connectionTimeout;

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getVirtualHost() {
        return virtualHost;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }
}
