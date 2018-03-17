package com.github.alexanderwe.bananaj.model.campaign;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum ContentType {


    @JsonProperty("template") TEMPLATE("template"),
    @JsonProperty("drag_and_drop") DRAG_AND_DROP("drag_and_drop"),
    @JsonProperty("html") HTML("html"),
    @JsonProperty("url") URL("url");

    private String stringRepresentation;

    ContentType(String stringRepresentation) {
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
