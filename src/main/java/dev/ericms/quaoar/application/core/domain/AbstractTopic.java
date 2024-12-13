package dev.ericms.quaoar.application.core.domain;

import java.util.UUID;

public class AbstractTopic {

    private UUID id;

    private String title;

    private Boolean active;

    public AbstractTopic(UUID id, String title, Boolean active) {
        this.id = id;
        this.title = title;
        this.active = active;
    }

    public AbstractTopic() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
