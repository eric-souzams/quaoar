package dev.ericms.quaoar.adapters.inbound.controller.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public class SendMailRequestDto {

    @NotBlank(message = "Field 'email' can't be empty")
    @Email(message = "Email should be valid")
    private String replyTo;

    @NotBlank(message = "Field 'subject' can't be empty")
    private String subject;

    @NotBlank(message = "Field 'content' can't be empty")
    private String content;

    @NotEmpty(message = "Field list 'recipients' can't be empty")
    private List<@Email(message = "Email should be valid") String> recipients;

    private String template;

    private Map<String, String> templateParams;

    private List<String> topics;

    private List<MultipartFile> attachments;

    public String getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(String replyTo) {
        this.replyTo = replyTo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<String> recipients) {
        this.recipients = recipients;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public List<String> getTopics() {
        return topics;
    }

    public void setTopics(List<String> topics) {
        this.topics = topics;
    }

    public List<MultipartFile> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<MultipartFile> attachments) {
        this.attachments = attachments;
    }

    public Map<String, String> getTemplateParams() {
        return templateParams;
    }

    public void setTemplateParams(Map<String, String> templateParams) {
        this.templateParams = templateParams;
    }
}
