package dev.ericms.quaoar.config.injection.topic;

import dev.ericms.quaoar.adapters.outbound.adapter.topic.FindTopicByNameAdapter;
import dev.ericms.quaoar.application.core.usecase.topic.FindTopicByNameUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FindTopicByNameBeanConfig {

    @Bean
    public FindTopicByNameUseCase findTopicByNameUseCase(FindTopicByNameAdapter findTopicByNameAdapter) {
        return new FindTopicByNameUseCase(findTopicByNameAdapter);
    }
}
