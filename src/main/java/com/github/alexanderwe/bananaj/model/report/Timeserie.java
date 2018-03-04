package com.github.alexanderwe.bananaj.model.report;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

public class Timeserie {

    @JsonProperty
    private LocalDateTime timestamp;
    @JsonProperty
    private int emails_sent;
    @JsonProperty
    private int unique_opens;
    @JsonProperty
    private int recipient_clicks;

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        OffsetDateTime offsetDateTime = OffsetDateTime.parse(timestamp);
        this.timestamp = offsetDateTime.toLocalDateTime();
    }

    public int getEmails_sent() {
        return emails_sent;
    }

    public void setEmails_sent(int emails_sent) {
        this.emails_sent = emails_sent;
    }

    public int getUnique_opens() {
        return unique_opens;
    }

    public void setUnique_opens(int unique_opens) {
        this.unique_opens = unique_opens;
    }

    public int getRecipient_clicks() {
        return recipient_clicks;
    }

    public void setRecipient_clicks(int recipient_clicks) {
        this.recipient_clicks = recipient_clicks;
    }
}
