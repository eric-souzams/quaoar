package dev.ericms.quaoar.config.injection.contact;

import dev.ericms.quaoar.adapters.outbound.adapter.contact.FindContactByEmailAdapter;
import dev.ericms.quaoar.application.core.usecase.contact.FindContactByEmailUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FindContactByEmailBeanConfig {

    @Bean
    public FindContactByEmailUseCase findContactByEmailUseCase(FindContactByEmailAdapter findContactByEmailAdapter) {
        return new FindContactByEmailUseCase(findContactByEmailAdapter);
    }

}
