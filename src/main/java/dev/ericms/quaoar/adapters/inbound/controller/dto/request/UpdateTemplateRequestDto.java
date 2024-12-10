package dev.ericms.quaoar.adapters.inbound.controller.dto.request;

import jakarta.validation.constraints.NotBlank;

public class UpdateTemplateRequestDto {

    @NotBlank(message = "Field 'name' can't be empty")
    private String name;

    @NotBlank(message = "Field 'title' can't be empty")
    private String title;

    @NotBlank(message = "Field 'content' can't be empty")
    private String content;

    @NotBlank(message = "Field 'active' can't be empty")
    private Boolean active;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
