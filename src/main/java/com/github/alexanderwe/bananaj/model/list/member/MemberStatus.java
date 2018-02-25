/**
 * @author alexanderweiss
 * @date 12.11.2015
 */
package com.github.alexanderwe.bananaj.model.list.member;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Possible status of a member of a list
 * @author alexanderweiss
 *
 */
public enum MemberStatus {

	@JsonProperty("pending")PENDING("pending"),
	@JsonProperty("subscribed")SUBSCRIBED("subscribed"),
    @JsonProperty("unsubscribed")UNSUBSCRIBED("unsubscribed"),
    @JsonProperty("cleaned")CLEANED("cleaned");
	
	private String stringRepresentation;
	
	MemberStatus(String stringRepresentation ){
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
