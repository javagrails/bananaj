package com.github.alexanderwe.bananaj.model.campaign;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public enum CampaignSendType {

    @JsonProperty("html") HTML("html"),
    @JsonProperty("plaintext") PLAINTEXT("plaintext");

    private final String value;
    private final static Map<String, CampaignSendType> CONSTANTS = new HashMap<String, CampaignSendType>();

    static {
        for (CampaignSendType c: values()) {
            CONSTANTS.put(c.value, c);
        }
    }

    private CampaignSendType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    public String value() {
        return value;
    }

    public static CampaignSendType fromValue(String value) {
        CampaignSendType constant = CONSTANTS.get(value);
        if (constant == null) {
            throw new IllegalArgumentException(value);
        } else {
            return constant;
        }
    }
}
