package dev.ericms.quaoar.application.core.dto;

import java.util.List;
import java.util.Objects;

public class SendEmailDto {

    private String subject;

    private String content;

    private List<String> recipientsTo;

    private List<String> recipientsCc;

    private String template;

    private String topic;

    public SendEmailDto() {
    }

    public SendEmailDto(String subject, String content, List<String> recipientsTo,
                        List<String> recipientsCc, String template, String topic) {
        this.subject = subject;
        this.content = content;
        this.recipientsTo = recipientsTo;
        this.recipientsCc = recipientsCc;
        this.template = template;
        this.topic = topic;
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

    public List<String> getRecipientsCc() {
        return recipientsCc;
    }

    public void setRecipientsCc(List<String> recipientsCc) {
        this.recipientsCc = recipientsCc;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SendEmailDto that = (SendEmailDto) o;
        return Objects.equals(subject, that.subject) && Objects.equals(content, that.content) && Objects.equals(recipientsTo, that.recipientsTo) && Objects.equals(recipientsCc, that.recipientsCc) && Objects.equals(template, that.template) && Objects.equals(topic, that.topic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subject, content, recipientsTo, recipientsCc, template, topic);
    }

    @Override
    public String toString() {
        return "SendEmailDto{" +
                "subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                ", recipientsTo=" + recipientsTo +
                ", recipientsCc=" + recipientsCc +
                ", template='" + template + '\'' +
                ", topic='" + topic + '\'' +
                '}';
    }
}
