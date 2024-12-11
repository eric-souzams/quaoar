package dev.ericms.quaoar.config.injection.template;

import dev.ericms.quaoar.adapters.outbound.adapter.template.DeleteTemplateAdapter;
import dev.ericms.quaoar.application.core.usecase.template.DeleteTemplateUseCase;
import dev.ericms.quaoar.application.core.usecase.template.FindTemplateByIdUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeleteTemplateBeanConfig {

    @Bean
    public DeleteTemplateUseCase deleteTemplateUseCase(DeleteTemplateAdapter deleteTemplateAdapter,
                                                       FindTemplateByIdUseCase findTemplateByIdUseCase) {
        return new DeleteTemplateUseCase(deleteTemplateAdapter, findTemplateByIdUseCase);
    }
}
