package dev.ericms.quaoar.config.injection.template;

import dev.ericms.quaoar.adapters.outbound.adapter.template.FindAllTemplateAdapter;
import dev.ericms.quaoar.application.core.usecase.template.FindAllTemplateUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FindAllTemplateBeanConfig {

    @Bean
    public FindAllTemplateUseCase findAllTemplateUseCase(FindAllTemplateAdapter findAllTemplateAdapter) {
        return new FindAllTemplateUseCase(findAllTemplateAdapter);
    }

}
