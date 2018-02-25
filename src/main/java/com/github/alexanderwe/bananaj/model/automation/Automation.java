/**
 * @author alexanderweiss
 * @date 30.11.2015
 */
package com.github.alexanderwe.bananaj.model.automation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.alexanderwe.bananaj.model.Link;
import com.github.alexanderwe.bananaj.model.MailchimpObject;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

/**
 * Class for representing an automation
 *
 * @author alexanderweiss
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Automation extends MailchimpObject {


    @JsonProperty
    private String id;
    @JsonProperty
    private LocalDateTime create_time;
    @JsonProperty
    private LocalDateTime start_time;
    @JsonProperty
    private AutomationStatus status;
    @JsonProperty
    private int emails_sent;
    @JsonProperty
    private Object recipients;
    @JsonProperty
    private Object settings;
    @JsonProperty
    private Object tracking;
    @JsonProperty
    private Object trigger_settings;
    @JsonProperty
    private Object report_summary;
    @JsonProperty
    private List<Link> _links;

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        OffsetDateTime offsetDateTime = OffsetDateTime.parse(create_time);
        this.create_time = offsetDateTime.toLocalDateTime();
    }

    public LocalDateTime getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        OffsetDateTime offsetDateTime = OffsetDateTime.parse(start_time);
        this.start_time = offsetDateTime.toLocalDateTime();
    }

    public AutomationStatus getStatus() {
        return status;
    }

    public void setStatus(AutomationStatus status) {
        this.status = status;
    }

    public int getEmails_sent() {
        return emails_sent;
    }

    public void setEmails_sent(int emails_sent) {
        this.emails_sent = emails_sent;
    }

    public Object getRecipients() {
        return recipients;
    }

    public void setRecipients(Object recipients) {
        this.recipients = recipients;
    }

    public Object getSettings() {
        return settings;
    }

    public void setSettings(Object settings) {
        this.settings = settings;
    }

    public Object getTracking() {
        return tracking;
    }

    public void setTracking(Object tracking) {
        this.tracking = tracking;
    }

    public Object getTrigger_settings() {
        return trigger_settings;
    }

    public void setTrigger_settings(Object trigger_settings) {
        this.trigger_settings = trigger_settings;
    }

    public Object getReport_summary() {
        return report_summary;
    }

    public void setReport_summary(Object report_summary) {
        this.report_summary = report_summary;
    }

    public List<Link> get_links() {
        return _links;
    }

    public void set_links(List<Link> _links) {
        this._links = _links;
    }
}
