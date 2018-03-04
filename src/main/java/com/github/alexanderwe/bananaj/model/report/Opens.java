/**
 * @author alexanderweiss
 * @date 12.12.2015
 */
package com.github.alexanderwe.bananaj.model.report;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

/**
 * Class for representing the opens of a campaign
 * @author alexanderweiss
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Opens {

	@JsonProperty
	private int opens_total;
	@JsonProperty
	private int unique_opens;
	@JsonProperty
	private double open_rate;
	@JsonProperty
	private LocalDateTime last_open;

	public int getOpens_total() {
		return opens_total;
	}

	public void setOpens_total(int opens_total) {
		this.opens_total = opens_total;
	}

	public int getUnique_opens() {
		return unique_opens;
	}

	public void setUnique_opens(int unique_opens) {
		this.unique_opens = unique_opens;
	}

	public double getOpen_rate() {
		return open_rate;
	}

	public void setOpen_rate(double open_rate) {
		this.open_rate = open_rate;
	}

	public LocalDateTime getLast_open() {
		return last_open;
	}

	public void setLast_open(String last_open) {
		OffsetDateTime offsetDateTime = OffsetDateTime.parse(last_open);
		this.last_open = offsetDateTime.toLocalDateTime();
	}
}
