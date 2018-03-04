package com.github.alexanderwe.bananaj.model.report;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

public class ABSplit {

    @JsonProperty
    private ABCampaign a;
    @JsonProperty
    private ABCampaign b;

    public ABCampaign getA() {
        return a;
    }

    public void setA(ABCampaign a) {
        this.a = a;
    }

    public ABCampaign getB() {
        return b;
    }

    public void setB(ABCampaign b) {
        this.b = b;
    }

    private class ABCampaign{

        @JsonProperty
        private int bounces;
        @JsonProperty
        private int abuse_reports;
        @JsonProperty
        private int unsubs;
        @JsonProperty
        private int recipient_clicks;
        @JsonProperty
        private int forwards;
        @JsonProperty
        private int forward_opens;
        @JsonProperty
        private int opens;
        @JsonProperty
        private LocalDateTime last_open;
        @JsonProperty
        private int unique_opens;

        public int getBounces() {
            return bounces;
        }

        public void setBounces(int bounces) {
            this.bounces = bounces;
        }

        public int getAbuse_reports() {
            return abuse_reports;
        }

        public void setAbuse_reports(int abuse_reports) {
            this.abuse_reports = abuse_reports;
        }

        public int getUnsubs() {
            return unsubs;
        }

        public void setUnsubs(int unsubs) {
            this.unsubs = unsubs;
        }

        public int getRecipient_clicks() {
            return recipient_clicks;
        }

        public void setRecipient_clicks(int recipient_clicks) {
            this.recipient_clicks = recipient_clicks;
        }

        public int getForwards() {
            return forwards;
        }

        public void setForwards(int forwards) {
            this.forwards = forwards;
        }

        public int getForward_opens() {
            return forward_opens;
        }

        public void setForward_opens(int forward_opens) {
            this.forward_opens = forward_opens;
        }

        public int getOpens() {
            return opens;
        }

        public void setOpens(int opens) {
            this.opens = opens;
        }

        public LocalDateTime getLast_open() {
            return last_open;
        }

        public void setLast_open(String last_open) {
            OffsetDateTime offsetDateTime = OffsetDateTime.parse(last_open);
            this.last_open = offsetDateTime.toLocalDateTime();
        }

        public int getUnique_opens() {
            return unique_opens;
        }

        public void setUnique_opens(int unique_opens) {
            this.unique_opens = unique_opens;
        }
    }


}
