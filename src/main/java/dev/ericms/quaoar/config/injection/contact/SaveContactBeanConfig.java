package dev.ericms.quaoar.config.injection.contact;

import dev.ericms.quaoar.adapters.outbound.adapter.contact.SaveContactAdapter;
import dev.ericms.quaoar.application.core.usecase.contact.SaveContactUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SaveContactBeanConfig {

    @Bean
    public SaveContactUseCase saveContactUseCase(SaveContactAdapter saveContactAdapter) {
        return new SaveContactUseCase(saveContactAdapter);
    }

}
