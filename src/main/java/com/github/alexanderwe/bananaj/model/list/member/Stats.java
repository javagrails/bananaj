package com.github.alexanderwe.bananaj.model.list.member;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Stats {

    @JsonProperty
    private double avg_open_rate;

    @JsonProperty
    private double avg_click_rate;

    public double getAvg_open_rate() {
        return avg_open_rate;
    }

    public void setAvg_open_rate(double avg_open_rate) {
        this.avg_open_rate = avg_open_rate;
    }

    public double getAvg_click_rate() {
        return avg_click_rate;
    }

    public void setAvg_click_rate(double avg_click_rate) {
        this.avg_click_rate = avg_click_rate;
    }
}
