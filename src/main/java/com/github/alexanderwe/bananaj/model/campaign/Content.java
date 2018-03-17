/**
 * @author alexanderweiss
 * @date 12.12.2015
 */
package com.github.alexanderwe.bananaj.model.campaign;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.alexanderwe.bananaj.model.Link;
import org.json.JSONObject;

import java.net.URL;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Content {

	@JsonProperty("variate_contents")
	private List<VariateContents> variate_contents;
	@JsonProperty("plain_text")
	private String plain_text;
	@JsonProperty("html")
	private String html;
	@JsonProperty("archive_html")
	private String archive_html;
	@JsonProperty
	List<Link> _links;

	public List<VariateContents> getVariate_contents() {
		return variate_contents;
	}

	public void setVariate_contents(List<VariateContents> variate_contents) {
		this.variate_contents = variate_contents;
	}

	public String getPlain_text() {
		return plain_text;
	}

	public void setPlain_text(String plain_text) {
		this.plain_text = plain_text;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public String getArchive_html() {
		return archive_html;
	}

	public void setArchive_html(String archive_html) {
		this.archive_html = archive_html;
	}

	public List<Link> get_links() {
		return _links;
	}

	public void set_links(List<Link> _links) {
		this._links = _links;
	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private class VariateContents {


		@JsonProperty("content_label")
		private String content_label;
		@JsonProperty("plain_text")
		private String plain_text;
		@JsonProperty("html")
		private String html;

		public String getContent_label() {
			return content_label;
		}

		public void setContent_label(String content_label) {
			this.content_label = content_label;
		}

		public String getPlain_text() {
			return plain_text;
		}

		public void setPlain_text(String plain_text) {
			this.plain_text = plain_text;
		}

		public String getHtml() {
			return html;
		}

		public void setHtml(String html) {
			this.html = html;
		}
	}

}
