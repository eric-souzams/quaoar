package dev.ericms.quaoar.config.injection.contact;

import dev.ericms.quaoar.adapters.outbound.adapter.contact.DeleteContactAdapter;
import dev.ericms.quaoar.application.core.usecase.contact.DeleteContactUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeleteContactBeanConfig {

    @Bean
    public DeleteContactUseCase deleteContactUseCase(DeleteContactAdapter deleteContactAdapter) {
        return new DeleteContactUseCase(deleteContactAdapter);
    }

}
