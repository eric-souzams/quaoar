package dev.ericms.quaoar.adapters.inbound.controller.dto.request;

import dev.ericms.quaoar.application.core.domain.Message;
import dev.ericms.quaoar.application.core.domain.Topic;
import dev.ericms.quaoar.config.annotations.annotation.AtLeastOneNotEmpty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@AtLeastOneNotEmpty(
        fields = {"recipientsTo", "recipientsCc", "recipientsBcc"},
        message = "At least one of 'recipientsTo' or 'recipientsCc' or 'recipientsBcc' must be filled"
)
public class SendMailRequestDto {

    @NotBlank(message = "Field 'email' can't be empty")
    @Email(message = "Email should be valid")
    private String replyTo;

    @NotBlank(message = "Field 'subject' can't be empty")
    private String subject;

    @NotBlank(message = "Field 'content' can't be empty")
    private String content;

    private List<@Email(message = "Email should be valid") String> recipientsTo = new ArrayList<>();

    private List<@Email(message = "Email should be valid") String> recipientsCc = new ArrayList<>();

    private List<@Email(message = "Email should be valid") String> recipientsBcc = new ArrayList<>();

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

    public List<String> getRecipientsTo() {
        return recipientsTo;
    }

    public void setRecipientsTo(List<String> recipientsTo) {
        this.recipientsTo = recipientsTo;
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

    public List<String> getRecipientsCc() {
        return recipientsCc;
    }

    public void setRecipientsCc(List<String> recipientsCc) {
        this.recipientsCc = recipientsCc;
    }

    public List<String> getRecipientsBcc() {
        return recipientsBcc;
    }

    public void setRecipientsBcc(List<String> recipientsBcc) {
        this.recipientsBcc = recipientsBcc;
    }

    public Message convertToMessage() {
        Message message = new Message();

        message.setSubject(this.subject);
        message.setContent(this.content);
        message.setTopics(topics == null ? null : this.topics.stream().map(Topic::new).toList());
        message.setRecipientsTo(this.recipientsTo.toString());
        message.setRecipientsCc(this.recipientsCc.toString());
        message.setRecipientsBcc(this.recipientsBcc.toString());

        return message;
    }

}
