package dev.ericms.quaoar.config.injection.message;

import dev.ericms.quaoar.adapters.outbound.adapter.message.SaveMessageAdapter;
import dev.ericms.quaoar.application.core.usecase.message.SaveMessageUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SaveMessageBeanConfig {

    @Bean
    public SaveMessageUseCase saveMessageUseCase(SaveMessageAdapter saveMessageAdapter) {
        return new SaveMessageUseCase(saveMessageAdapter);
    }

}
