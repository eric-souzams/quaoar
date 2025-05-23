package dev.ericms.quaoar.application.core.domain;

import java.util.UUID;

public class AbstractTopic {

    private UUID id;

    private String name;

    private Boolean active;

    public AbstractTopic(UUID id, String name, Boolean active) {
        this.id = id;
        this.name = name;
        this.active = active;
    }

    public AbstractTopic(String name) {
        this.name = name;
    }

    public AbstractTopic() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
