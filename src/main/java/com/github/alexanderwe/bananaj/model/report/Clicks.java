/**
 * @author alexanderweiss
 * @date 20.11.2015
 */
package com.github.alexanderwe.bananaj.model.report;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

/**
 * Class for representing clicks for a campaign
 * @author alexanderweiss
 *
 */
public class Clicks {

	@JsonProperty
	private int clicks_total;
	@JsonProperty
	private int unique_clicks;
	@JsonProperty
	private int unique_subscriber_clicks;
	@JsonProperty
	private double click_rate;
	@JsonProperty
	private LocalDateTime last_click;

	public int getClicks_total() {
		return clicks_total;
	}

	public void setClicks_total(int clicks_total) {
		this.clicks_total = clicks_total;
	}

	public int getUnique_clicks() {
		return unique_clicks;
	}

	public void setUnique_clicks(int unique_clicks) {
		this.unique_clicks = unique_clicks;
	}

	public int getUnique_subscriber_clicks() {
		return unique_subscriber_clicks;
	}

	public void setUnique_subscriber_clicks(int unique_subscriber_clicks) {
		this.unique_subscriber_clicks = unique_subscriber_clicks;
	}

	public double getClick_rate() {
		return click_rate;
	}

	public void setClick_rate(double click_rate) {
		this.click_rate = click_rate;
	}

	public LocalDateTime getLast_click() {
		return last_click;
	}

	public void setLast_click(String last_click) {
		OffsetDateTime offsetDateTime = OffsetDateTime.parse(last_click);
		this.last_click = offsetDateTime.toLocalDateTime();
	}
}
