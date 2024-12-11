package dev.ericms.quaoar.config.injection.template;

import dev.ericms.quaoar.application.core.usecase.template.FindTemplateByIdUseCase;
import dev.ericms.quaoar.application.core.usecase.template.SaveTemplateUseCase;
import dev.ericms.quaoar.application.core.usecase.template.UpdateTemplateUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UpdateTemplateBeanConfig {

    @Bean
    public UpdateTemplateUseCase updateTemplateUseCase(SaveTemplateUseCase saveTemplateUseCase,
                                                       FindTemplateByIdUseCase findTemplateByIdUseCase) {
        return new UpdateTemplateUseCase(saveTemplateUseCase, findTemplateByIdUseCase);
    }

}
