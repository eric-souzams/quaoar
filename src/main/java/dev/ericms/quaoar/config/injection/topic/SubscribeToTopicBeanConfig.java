package dev.ericms.quaoar.config.injection.topic;

import dev.ericms.quaoar.adapters.outbound.adapter.topic.SubscribeToTopicAdapter;
import dev.ericms.quaoar.application.core.usecase.topic.SubscribeToTopicUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SubscribeToTopicBeanConfig {

    @Bean
    public SubscribeToTopicUseCase subscribeToTopicUseCase(SubscribeToTopicAdapter subscribeToTopicAdapter) {
        return new SubscribeToTopicUseCase(subscribeToTopicAdapter);
    }

}
