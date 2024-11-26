package dev.ericms.quaoar.application.core.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SubscribeOrUnSubscribeToTopicDto {

    private String email;

    private String integrationId;

    private List<String> topics;

    public SubscribeOrUnSubscribeToTopicDto() {
        this.topics = new ArrayList<>();
    }

    public SubscribeOrUnSubscribeToTopicDto(String email, String integrationId, List<String> topics) {
        this.email = email;
        this.integrationId = integrationId;
        this.topics = topics;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIntegrationId() {
        return integrationId;
    }

    public void setIntegrationId(String integrationId) {
        this.integrationId = integrationId;
    }

    public List<String> getTopics() {
        return topics;
    }

    public void setTopics(List<String> topics) {
        this.topics = topics;
    }

    public void addTopic(String topic) {
        this.topics.add(topic);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SubscribeOrUnSubscribeToTopicDto that = (SubscribeOrUnSubscribeToTopicDto) o;
        return Objects.equals(email, that.email) && Objects.equals(integrationId, that.integrationId) && Objects.equals(topics, that.topics);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, integrationId, topics);
    }

    @Override
    public String toString() {
        return "SubscribeOrUnSubscribeToTopicDto{" +
                "email='" + email + '\'' +
                ", integrationId='" + integrationId + '\'' +
                ", topics=" + topics +
                '}';
    }
}
