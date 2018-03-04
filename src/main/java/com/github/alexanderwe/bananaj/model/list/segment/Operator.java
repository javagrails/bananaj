package com.github.alexanderwe.bananaj.model.list.segment;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by alexanderweiss on 27.12.16.
 */
public enum Operator {

    @JsonProperty("is") IS("is"),
    @JsonProperty("not") NOT("not"),
    @JsonProperty("notcontain") NOTCONTAIN("notcontain"),
    @JsonProperty("interestcontains") INTERESTCONTAINS("interestcontains"),
    @JsonProperty("contains") CONTAINS("contains"),
    @JsonProperty("starts") STARTS("starts"),
    @JsonProperty("ends") ENDS("ends"),
    @JsonProperty("greater") GREATER("greater"),
    @JsonProperty("less") LESS("less");

    private String stringRepresentation;

    Operator(String stringRepresentation ){
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
