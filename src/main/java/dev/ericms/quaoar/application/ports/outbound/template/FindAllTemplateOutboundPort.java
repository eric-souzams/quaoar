package dev.ericms.quaoar.application.ports.outbound.template;

import dev.ericms.quaoar.application.core.domain.Template;
import dev.ericms.quaoar.application.core.dto.PageResponseDTO;
import dev.ericms.quaoar.application.core.enums.SortDirection;

public interface FindAllTemplateOutboundPort {

    PageResponseDTO<Template> findAll(SortDirection sort, int page, int size);

}
