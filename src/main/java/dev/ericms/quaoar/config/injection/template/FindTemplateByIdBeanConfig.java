package dev.ericms.quaoar.config.injection.template;

import dev.ericms.quaoar.adapters.outbound.adapter.template.FindTemplateByIdAdapter;
import dev.ericms.quaoar.application.core.usecase.template.FindTemplateByIdUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FindTemplateByIdBeanConfig {

    @Bean
    public FindTemplateByIdUseCase findTemplateByIdUseCase(FindTemplateByIdAdapter findTemplateByIdAdapter) {
        return new FindTemplateByIdUseCase(findTemplateByIdAdapter);
    }

}
