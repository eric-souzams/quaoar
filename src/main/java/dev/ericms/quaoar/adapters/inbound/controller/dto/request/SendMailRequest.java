package dev.ericms.quaoar.adapters.inbound.controller.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class SendMailRequest {

    @NotBlank(message = "Field 'email' can't be empty")
    @Email(message = "Email should be valid")
    private String replyTo;

    @NotBlank(message = "Field 'subject' can't be empty")
    private String subject;

    @NotBlank(message = "Field 'body' can't be empty")
    private String body;

    @NotEmpty(message = "Field list 'recipients' can't be empty")
    private List<@Email(message = "Email should be valid") String> recipients;

    private String template_id;

    private String topic;

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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<String> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<String> recipients) {
        this.recipients = recipients;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public List<MultipartFile> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<MultipartFile> attachments) {
        this.attachments = attachments;
    }
}
