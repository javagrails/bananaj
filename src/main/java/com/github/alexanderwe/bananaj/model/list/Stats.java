package com.github.alexanderwe.bananaj.model.list;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Stats {

    @JsonProperty
    private int member_count;
    @JsonProperty
    private int unsubscribe_count;
    @JsonProperty
    private int cleaned_count;
    @JsonProperty
    private int member_count_since_send;
    @JsonProperty
    private int unsubscribe_count_since_send;
    @JsonProperty
    private int cleaned_count_since_send;
    @JsonProperty
    private int campaign_count;
    @JsonProperty
    private LocalDateTime campaign_last_sent;
    @JsonProperty
    private int merge_field_count;
    @JsonProperty
    private double avg_sub_rate;
    @JsonProperty
    private double sub_rate; // Used inside the report list_stats
    @JsonProperty
    private double avg_unsub_rate;
    @JsonProperty
    private double target_sub_rate;
    @JsonProperty
    private double open_rate;
    @JsonProperty
    private double click_rate;
    @JsonProperty
    private LocalDateTime last_sub_date;
    @JsonProperty
    private LocalDateTime last_unsub_date;


    public int getMember_count() {
        return member_count;
    }

    public void setMember_count(int member_count) {
        this.member_count = member_count;
    }

    public int getUnsubscribe_count() {
        return unsubscribe_count;
    }

    public void setUnsubscribe_count(int unsubscribe_count) {
        this.unsubscribe_count = unsubscribe_count;
    }

    public int getCleaned_count() {
        return cleaned_count;
    }

    public void setCleaned_count(int cleaned_count) {
        this.cleaned_count = cleaned_count;
    }

    public int getMember_count_since_send() {
        return member_count_since_send;
    }

    public void setMember_count_since_send(int member_count_since_send) {
        this.member_count_since_send = member_count_since_send;
    }

    public int getUnsubscribe_count_since_send() {
        return unsubscribe_count_since_send;
    }

    public void setUnsubscribe_count_since_send(int unsubscribe_count_since_send) {
        this.unsubscribe_count_since_send = unsubscribe_count_since_send;
    }

    public int getCleaned_count_since_send() {
        return cleaned_count_since_send;
    }

    public void setCleaned_count_since_send(int cleaned_count_since_send) {
        this.cleaned_count_since_send = cleaned_count_since_send;
    }

    public int getCampaign_count() {
        return campaign_count;
    }

    public void setCampaign_count(int campaign_count) {
        this.campaign_count = campaign_count;
    }

    public LocalDateTime getCampaign_last_sent() {
        return campaign_last_sent;
    }

    public void setCampaign_last_sent(String campaign_last_sent) {
        if (campaign_last_sent.isEmpty()) {
            this.campaign_last_sent = null;
        } else {
            OffsetDateTime offsetDateTime = OffsetDateTime.parse(campaign_last_sent);
            this.campaign_last_sent = offsetDateTime.toLocalDateTime();
        }
    }

    public int getMerge_field_count() {
        return merge_field_count;
    }

    public void setMerge_field_count(int merge_field_count) {
        this.merge_field_count = merge_field_count;
    }

    public double getAvg_sub_rate() {
        return avg_sub_rate;
    }

    public void setAvg_sub_rate(double avg_sub_rate) {
        this.avg_sub_rate = avg_sub_rate;
    }

    public double getAvg_unsub_rate() {
        return avg_unsub_rate;
    }

    public void setAvg_unsub_rate(double avg_unsub_rate) {
        this.avg_unsub_rate = avg_unsub_rate;
    }

    public double getTarget_sub_rate() {
        return target_sub_rate;
    }

    public void setTarget_sub_rate(double target_sub_rate) {
        this.target_sub_rate = target_sub_rate;
    }

    public double getOpen_rate() {
        return open_rate;
    }

    public void setOpen_rate(double open_rate) {
        this.open_rate = open_rate;
    }

    public double getClick_rate() {
        return click_rate;
    }

    public void setClick_rate(double click_rate) {
        this.click_rate = click_rate;
    }

    public LocalDateTime getLast_sub_date() {
        return last_sub_date;
    }

    public void setLast_sub_date(String last_sub_date) {
        if (last_sub_date.isEmpty()) {
            this.last_sub_date = null;
        } else {
            OffsetDateTime offsetDateTime = OffsetDateTime.parse(last_sub_date);
            this.last_sub_date = offsetDateTime.toLocalDateTime();
        }
    }

    public LocalDateTime getLast_unsub_date() {
        return last_unsub_date;
    }

    public void setLast_unsub_date(String last_unsub_date) {
        if (last_unsub_date.isEmpty()) {
            this.last_unsub_date = null;
        } else {
            OffsetDateTime offsetDateTime = OffsetDateTime.parse(last_unsub_date);
            this.last_unsub_date = offsetDateTime.toLocalDateTime();
        }
    }


    public double getSub_rate() {
        return sub_rate;
    }

    public void setSub_rate(double sub_rate) {
        this.sub_rate = sub_rate;
    }
}
