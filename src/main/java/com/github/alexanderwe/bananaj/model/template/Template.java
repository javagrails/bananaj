/**
 * @author alexanderweiss
 * @date 19.11.2015
 */
package com.github.alexanderwe.bananaj.model.template;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.alexanderwe.bananaj.connection.MailChimpConnection;
import com.github.alexanderwe.bananaj.exceptions.MailchimpAPIException;
import com.github.alexanderwe.bananaj.model.Link;
import com.github.alexanderwe.bananaj.model.MailchimpObject;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import org.json.JSONObject;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class Template extends MailchimpObject  {

	private MailChimpConnection connection;

	@JsonProperty
	private String id;
	@JsonProperty
	private TemplateType type;
	@JsonProperty
	private String name;
	@JsonProperty
	private boolean drag_and_drop;
	@JsonProperty
	private String html;
	@JsonProperty
	private String category;
	@JsonProperty
	private LocalDateTime date_created;
	@JsonProperty
	private String created_by;
	@JsonProperty
	private boolean active;
	@JsonProperty
	private String folder_id;
	@JsonProperty
	private String thumbnail;
	@JsonProperty
	private String share_url;
	@JsonProperty
	private List<Link> _links;

	



	/**
	 * Change the name of this template
	 * @param name
	 * @throws Exception
	 */
	public void changeName(String name) throws Exception{

		Template template = new Template();
		template.setName(name);

		HttpResponse<JsonNode> postReponse = Unirest.patch(this.connection.getTemplateendpoint()+"/"+this.getId())
				.header("Authorization", this.connection.getApikey())
				.header("accept", "application/json")
				.header("Content-Type", "application/json")
				.body(template)
				.asJson();

		if (postReponse.getStatus() / 100 != 2) {
			throw new MailchimpAPIException(postReponse.getBody().getObject());
		}
		this.name = name;
	}

	/**
	 * Change the html content of this template
	 * @param html
	 * @throws Exception
	 */
	public void changeHTML(String html) throws Exception{
		Template template = new Template();
		template.setHtml(html);

		HttpResponse<JsonNode> postReponse = Unirest.patch(this.connection.getTemplateendpoint()+"/"+this.getId())
				.header("Authorization", this.connection.getApikey())
				.header("accept", "application/json")
				.header("Content-Type", "application/json")
				.body(template)
				.asJson();

		if (postReponse.getStatus() / 100 != 2) {
			throw new MailchimpAPIException(postReponse.getBody().getObject());
		}

		this.html = html;
	}

	/**
	 * Change the folder of this template
	 * @param folder_id
	 * @throws Exception
	 */
	public void changeFolder(String folder_id) throws Exception{
		Template template = new Template();
		template.setFolder_id(folder_id);

		HttpResponse<JsonNode> postReponse = Unirest.patch(this.connection.getTemplateendpoint()+"/"+this.getId())
				.header("Authorization", this.connection.getApikey())
				.header("accept", "application/json")
				.header("Content-Type", "application/json")
				.body(template)
				.asJson();

		if (postReponse.getStatus() / 100 != 2) {
			throw new MailchimpAPIException(postReponse.getBody().getObject());
		}

		this.folder_id = folder_id;
	}


	@Override
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public TemplateType getType() {
		return type;
	}

	public void setType(TemplateType type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isDrag_and_drop() {
		return drag_and_drop;
	}

	public void setDrag_and_drop(boolean drag_and_drop) {
		this.drag_and_drop = drag_and_drop;
	}


	public String isHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}


	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public LocalDateTime getDate_created() {
		return date_created;
	}

	public void setDate_created(LocalDateTime date_created) {
		this.date_created = date_created;
	}

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getFolder_id() {
		return folder_id;
	}

	public void setFolder_id(String folder_id) {
		this.folder_id = folder_id;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getShare_url() {
		return share_url;
	}

	public void setShare_url(String share_url) {
		this.share_url = share_url;
	}

	public List<Link> get_links() {
		return _links;
	}

	public void set_links(List<Link> _links) {
		this._links = _links;
	}

	public MailChimpConnection getConnection() {
		return connection;
	}

	public void setConnection(MailChimpConnection connection) {
		this.connection = connection;
	}
}
