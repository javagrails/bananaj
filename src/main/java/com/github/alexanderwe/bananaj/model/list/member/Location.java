package com.github.alexanderwe.bananaj.model.list.member;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Location {

    @JsonProperty
    private double latitude;
    @JsonProperty
    private double longitude;
    @JsonProperty
    private int gmtoff;
    @JsonProperty
    private int dstoff;
    @JsonProperty
    private String country_code;
    @JsonProperty
    private String timezone;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getGmtoff() {
        return gmtoff;
    }

    public void setGmtoff(int gmtoff) {
        this.gmtoff = gmtoff;
    }

    public int getDstoff() {
        return dstoff;
    }

    public void setDstoff(int dstoff) {
        this.dstoff = dstoff;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }
}
