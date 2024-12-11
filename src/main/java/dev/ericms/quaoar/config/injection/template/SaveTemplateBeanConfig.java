package dev.ericms.quaoar.config.injection.template;

import dev.ericms.quaoar.adapters.outbound.adapter.template.SaveTemplateAdapter;
import dev.ericms.quaoar.application.core.usecase.template.SaveTemplateUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SaveTemplateBeanConfig {

    @Bean
    public SaveTemplateUseCase saveTemplateUseCase(SaveTemplateAdapter saveTemplateAdapter) {
        return new SaveTemplateUseCase(saveTemplateAdapter);
    }
}
