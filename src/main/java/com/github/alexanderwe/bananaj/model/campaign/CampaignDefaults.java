/**
 * @author alexanderweiss
 * @date 05.12.2015
 */
package com.github.alexanderwe.bananaj.model.campaign;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CampaignDefaults {


	@JsonProperty
	private String from_name;

	@JsonProperty
	private String from_email;

	@JsonProperty
	private String subject;

	@JsonProperty
	private String language;
	
	public CampaignDefaults(String from_name, String from_email, String subject, String language) {
		this.from_name = from_name;
		this.from_email = from_email;
		this.subject = subject;
		this.language = language;
	}

	/**
	 * @return the from_name
	 */
	public String getFrom_name() {
		return from_name;
	}

	/**
	 * @return the from_email
	 */
	public String getFrom_email() {
		return from_email;
	}

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}

}
