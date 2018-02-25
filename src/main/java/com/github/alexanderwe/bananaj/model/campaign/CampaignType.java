/**
 * @author alexanderweiss
 * @date 20.11.2015
 */
package com.github.alexanderwe.bananaj.model.campaign;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Enum for representing different campaign types
 */
public enum CampaignType {

    @JsonProperty("A") A("A"), //NOTE: strange type from API
    @JsonProperty("regular") REGULAR("regular"),
    @JsonProperty("plaintext") PLAINTEXT("plaintext"),
    @JsonProperty("absplit") ABSPLIT("absplit"),
    @JsonProperty("rss") RSS("rss"),
    @JsonProperty("automation") AUTOMATION("automation"),
    @JsonProperty("variate") VARIATE("variate"),
    @JsonProperty("auto") AUTO("auto");

    private String stringRepresentation;

    CampaignType(String stringRepresentation) {
        setStringRepresentation(stringRepresentation);
    }

    /**
     * @return the stringRepresentation
     */
    public String getStringRepresentation() {
        return stringRepresentation;
    }

    /**
     * @param stringRepresentation the stringRepresentation to set
     */
    private void setStringRepresentation(String stringRepresentation) {
        this.stringRepresentation = stringRepresentation;
    }

}
