package dev.ericms.quaoar.application.core.usecase.message;

import dev.ericms.quaoar.application.core.domain.Message;
import dev.ericms.quaoar.application.core.dto.PageResponseDTO;
import dev.ericms.quaoar.application.core.enums.SortDirection;
import dev.ericms.quaoar.application.ports.inbound.message.FindAllMessagesInboundPort;
import dev.ericms.quaoar.application.ports.outbound.message.FindAllMessagesOutboundPort;

public class FindAllMessagesUseCase implements FindAllMessagesInboundPort {

    private final FindAllMessagesOutboundPort findAllMessagesOutboundPort;

    public FindAllMessagesUseCase(FindAllMessagesOutboundPort findAllMessagesOutboundPort) {
        this.findAllMessagesOutboundPort = findAllMessagesOutboundPort;
    }

    @Override
    public PageResponseDTO<Message> findAll(SortDirection sort, int page, int size) {
        return findAllMessagesOutboundPort.findAll(sort, page, size);
    }
}
