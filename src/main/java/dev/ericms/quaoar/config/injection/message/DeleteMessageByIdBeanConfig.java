package dev.ericms.quaoar.config.injection.message;

import dev.ericms.quaoar.adapters.outbound.adapter.message.DeleteMessageByIdAdapter;
import dev.ericms.quaoar.application.core.usecase.message.DeleteMessageByIdUseCase;
import dev.ericms.quaoar.application.core.usecase.message.FindMessageByIdUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeleteMessageByIdBeanConfig {

    @Bean
    public DeleteMessageByIdUseCase deleteMessageByIdUseCase(DeleteMessageByIdAdapter deleteMessageByIdAdapter,
                                                             FindMessageByIdUseCase findMessageByIdUseCase) {
        return new DeleteMessageByIdUseCase(deleteMessageByIdAdapter, findMessageByIdUseCase);
    }

}
