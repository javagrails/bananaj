package com.github.alexanderwe.bananaj.model.list.segment;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by alexanderweiss on 27.12.16.
 */
public enum MatchType {

    @JsonProperty("any")
    ANY("any"),
    @JsonProperty("all") ALL("all");

    private String stringRepresentation;

    MatchType(String stringRepresentation ){
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
