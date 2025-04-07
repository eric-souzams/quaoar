package dev.ericms.quaoar.adapters.outbound.mapper;

import dev.ericms.quaoar.adapters.outbound.repository.entity.TopicEntity;
import dev.ericms.quaoar.application.core.domain.Topic;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TopicOutboundMapper {

    TopicEntity toEntity(Topic topic);

    Topic toDomain(TopicEntity topicEntity);

}
