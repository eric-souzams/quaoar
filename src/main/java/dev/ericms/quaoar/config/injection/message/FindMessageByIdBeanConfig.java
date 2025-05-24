package dev.ericms.quaoar.config.injection.message;

import dev.ericms.quaoar.adapters.outbound.adapter.message.FindMessageByIdAdapter;
import dev.ericms.quaoar.adapters.outbound.adapter.topic.FindTopicsByMessageIdAdapter;
import dev.ericms.quaoar.application.core.usecase.message.FindMessageByIdUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FindMessageByIdBeanConfig {

    @Bean
    public FindMessageByIdUseCase findMessageByIdUseCase(FindMessageByIdAdapter findMessageByIdAdapter,
                                                         FindTopicsByMessageIdAdapter findTopicsByMessageIdAdapter) {
        return new FindMessageByIdUseCase(findMessageByIdAdapter, findTopicsByMessageIdAdapter);
    }

}
