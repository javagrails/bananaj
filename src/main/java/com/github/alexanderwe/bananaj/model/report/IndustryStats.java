/**
 * @author alexanderweiss
 * @date 13.12.2015
 */
package com.github.alexanderwe.bananaj.model.report;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class for representing the average campaign statistics for your industry
 * @author alexanderweiss
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IndustryStats {

	@JsonProperty
	private String type;
	@JsonProperty
	private double open_rate;
	@JsonProperty
	private double click_rate;
	@JsonProperty
	private double bounce_rate;
	@JsonProperty
	private double unopen_rate;
	@JsonProperty
	private double unsub_rate;
	@JsonProperty
	private double abuse_rate;


	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @return the open_rate
	 */
	public double getOpen_rate() {
		return open_rate;
	}

	/**
	 * @return the click_rate
	 */
	public double getClick_rate() {
		return click_rate;
	}

	/**
	 * @return the bounce_rate
	 */
	public double getBounce_rate() {
		return bounce_rate;
	}

	/**
	 * @return the unopen_rate
	 */
	public double getUnopen_rate() {
		return unopen_rate;
	}

	/**
	 * @return the unsub_rate
	 */
	public double getUnsub_rate() {
		return unsub_rate;
	}

	/**
	 * @return the abuse_rate
	 */
	public double getAbuse_rate() {
		return abuse_rate;
	}
}
