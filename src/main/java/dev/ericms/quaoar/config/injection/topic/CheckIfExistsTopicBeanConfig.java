package dev.ericms.quaoar.config.injection.topic;

import dev.ericms.quaoar.adapters.outbound.adapter.topic.CheckIfExistsTopicAdapter;
import dev.ericms.quaoar.application.core.usecase.topic.CheckIfExistsTopicUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CheckIfExistsTopicBeanConfig {

    @Bean
    public CheckIfExistsTopicUseCase checkIfExistsTopicUseCase(CheckIfExistsTopicAdapter checkIfExistsTopicAdapter) {
        return new CheckIfExistsTopicUseCase(checkIfExistsTopicAdapter);
    }
}
