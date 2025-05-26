package dev.ericms.quaoar.application.ports.outbound.message;

import dev.ericms.quaoar.application.core.domain.Message;
import dev.ericms.quaoar.application.core.dto.PageResponseDTO;
import dev.ericms.quaoar.application.core.enums.SortDirection;

public interface FindAllMessagesOutboundPort {

    PageResponseDTO<Message> findAll(SortDirection sort, int page, int size);
}
