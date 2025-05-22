package dev.ericms.quaoar.config.injection.template;

import dev.ericms.quaoar.adapters.outbound.adapter.template.FindTemplateByNameAdapter;
import dev.ericms.quaoar.application.core.usecase.template.FindTemplateByNameUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FindTemplateByNameBeanConfig {

    @Bean
    public FindTemplateByNameUseCase findTemplateByNameUseCase(FindTemplateByNameAdapter findTemplateByNameAdapter) {
        return new FindTemplateByNameUseCase(findTemplateByNameAdapter);
    }

}
