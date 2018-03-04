package com.github.alexanderwe.bananaj.model.report;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

public class TimeWarp {


    @JsonProperty
    private int gmt_offset;
    @JsonProperty
    private int opens;
    @JsonProperty
    private LocalDateTime last_open;
    @JsonProperty
    private int unqiue_opens;
    @JsonProperty
    private int clicks;
    @JsonProperty
    private LocalDateTime last_click;
    @JsonProperty
    private int unique_clicks;
    @JsonProperty
    private int bounces;

    public int getGmt_offset() {
        return gmt_offset;
    }

    public void setGmt_offset(int gmt_offset) {
        this.gmt_offset = gmt_offset;
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

    public int getUnqiue_opens() {
        return unqiue_opens;
    }

    public void setUnqiue_opens(int unqiue_opens) {
        this.unqiue_opens = unqiue_opens;
    }

    public int getClicks() {
        return clicks;
    }

    public void setClicks(int clicks) {
        this.clicks = clicks;
    }

    public LocalDateTime getLast_click() {
        return last_click;
    }

    public void setLast_click(String last_click) {
        OffsetDateTime offsetDateTime = OffsetDateTime.parse(last_click);
        this.last_click = offsetDateTime.toLocalDateTime();
    }

    public int getUnique_clicks() {
        return unique_clicks;
    }

    public void setUnique_clicks(int unique_clicks) {
        this.unique_clicks = unique_clicks;
    }

    public int getBounces() {
        return bounces;
    }

    public void setBounces(int bounces) {
        this.bounces = bounces;
    }
}
