package com.github.alexanderwe.bananaj.model.list.segment;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by alexanderweiss on 04.02.16.
 */
public enum SegmentType {

    @JsonProperty("static") STATIC("static"),
    @JsonProperty("fuzzy") FUZZY("fuzzy"),
    @JsonProperty("absplit") ABSPLIT("absplit"),
    @JsonProperty("saved") SAVED("saved");

    private String stringRepresentation;

    SegmentType(String stringRepresentation) {
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
    public void setStringRepresentation(String stringRepresentation) {
        this.stringRepresentation = stringRepresentation;
    }

}
