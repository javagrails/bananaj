/**
 * @author alexanderweiss
 * @date 30.11.2015
 */
package com.github.alexanderwe.bananaj.model.automation;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum AutomationStatus {

	@JsonProperty("save") SAVE("save"),
    @JsonProperty("paused") PAUSED("paused"),
    @JsonProperty("sending") SENDING("sending");

	private String stringRepresentation;
	
	AutomationStatus(String stringRepresentation){
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


