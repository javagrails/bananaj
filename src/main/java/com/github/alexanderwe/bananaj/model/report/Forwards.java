/**
 * @author alexanderweiss
 * @date 20.11.2015
 */
package com.github.alexanderwe.bananaj.model.report;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class for representing a forward in a campaign
 * @author alexanderweiss
 *
 */
public class Forwards {

	@JsonProperty
	private int forwards_count;
	private int forwards_open;

	public int getForwards_count() {
		return forwards_count;
	}

	public void setForwards_count(int forwards_count) {
		this.forwards_count = forwards_count;
	}

	public int getForwards_open() {
		return forwards_open;
	}

	public void setForwards_open(int forwards_open) {
		this.forwards_open = forwards_open;
	}
}
