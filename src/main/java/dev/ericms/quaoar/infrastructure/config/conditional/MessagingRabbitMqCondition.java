package dev.ericms.quaoar.infrastructure.config.conditional;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class MessagingRabbitMqCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Environment env = context.getEnvironment();
        String activeType = env.getProperty("messaging.active");
        assert activeType != null;
        return activeType.equals("RABBITMQ");
    }
}
