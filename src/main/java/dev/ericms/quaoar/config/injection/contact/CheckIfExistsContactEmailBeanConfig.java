package dev.ericms.quaoar.config.injection.contact;

import dev.ericms.quaoar.adapters.outbound.adapter.contact.CheckIfExistsContactEmailAdapter;
import dev.ericms.quaoar.application.core.usecase.contact.CheckIfExistsContactEmailUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CheckIfExistsContactEmailBeanConfig {

    @Bean
    public CheckIfExistsContactEmailUseCase checkIfExistsContactEmailUseCase(CheckIfExistsContactEmailAdapter checkIfExistsContactEmailAdapter) {
        return new CheckIfExistsContactEmailUseCase(checkIfExistsContactEmailAdapter);
    }

}
