package com.github.alexanderwe.bananaj.model.report;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ShareReport {

    @JsonProperty
    private String share_url;
    @JsonProperty
    private String share_password;

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public String getShare_password() {
        return share_password;
    }

    public void setShare_password(String share_password) {
        this.share_password = share_password;
    }
}
