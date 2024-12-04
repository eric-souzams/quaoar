package dev.ericms.quaoar.config.injection.topic;

import dev.ericms.quaoar.adapters.outbound.adapter.topic.UnsubscribeToTopicAdapter;
import dev.ericms.quaoar.application.core.usecase.topic.UnsubscribeToTopicUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UnsubscribeToTopicBeanConfig {

    @Bean
    public UnsubscribeToTopicUseCase unsubscribeToTopicUseCase(UnsubscribeToTopicAdapter unsubscribeToTopicAdapter) {
        return new UnsubscribeToTopicUseCase(unsubscribeToTopicAdapter);
    }

}
