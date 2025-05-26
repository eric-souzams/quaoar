package dev.ericms.quaoar.config.injection.message;

import dev.ericms.quaoar.adapters.outbound.adapter.message.FindAllMessagesAdapter;
import dev.ericms.quaoar.application.core.usecase.message.FindAllMessagesUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FindAllMessagesBeanConfig {

    @Bean
    public FindAllMessagesUseCase findAllMessagesUseCase(FindAllMessagesAdapter findAllMessagesAdapter) {
        return new FindAllMessagesUseCase(findAllMessagesAdapter);
    }
}
