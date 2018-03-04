/**
 * @author alexanderweiss
 * @date 20.11.2015
 */
package com.github.alexanderwe.bananaj.model.campaign;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class representing a bounce for a campaign
 * @author alexanderweiss
 *
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Bounces {

	@JsonProperty
	private int hard_bounces;
	@JsonProperty
	private int soft_bounces;
	@JsonProperty
	private int syntax_error_bounces;

	public int getHard_bounces() {
		return hard_bounces;
	}

	public void setHard_bounces(int hard_bounces) {
		this.hard_bounces = hard_bounces;
	}

	public int getSoft_bounces() {
		return soft_bounces;
	}

	public void setSoft_bounces(int soft_bounces) {
		this.soft_bounces = soft_bounces;
	}

	public int getSyntax_error_bounces() {
		return syntax_error_bounces;
	}

	public void setSyntax_error_bounces(int syntax_error_bounces) {
		this.syntax_error_bounces = syntax_error_bounces;
	}
}
