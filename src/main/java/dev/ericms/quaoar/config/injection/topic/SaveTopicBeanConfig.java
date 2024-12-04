package dev.ericms.quaoar.config.injection.topic;

import dev.ericms.quaoar.adapters.outbound.adapter.topic.SaveTopicAdapter;
import dev.ericms.quaoar.application.core.usecase.topic.SaveTopicUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SaveTopicBeanConfig {

    @Bean
    public SaveTopicUseCase saveTopicUseCase(SaveTopicAdapter saveTopicAdapter) {
        return new SaveTopicUseCase(saveTopicAdapter);
    }

}
