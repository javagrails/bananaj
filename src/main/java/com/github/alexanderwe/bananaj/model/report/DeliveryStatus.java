package com.github.alexanderwe.bananaj.model.report;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DeliveryStatus {



    @JsonProperty
    private boolean enabled;
    @JsonProperty
    private boolean can_cancel;
    @JsonProperty
    private String status;
    @JsonProperty
    private int emails_sent;
    @JsonProperty
    private int emails_canceled;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isCan_cancel() {
        return can_cancel;
    }

    public void setCan_cancel(boolean can_cancel) {
        this.can_cancel = can_cancel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getEmails_sent() {
        return emails_sent;
    }

    public void setEmails_sent(int emails_sent) {
        this.emails_sent = emails_sent;
    }

    public int getEmails_canceled() {
        return emails_canceled;
    }

    public void setEmails_canceled(int emails_canceled) {
        this.emails_canceled = emails_canceled;
    }
}
