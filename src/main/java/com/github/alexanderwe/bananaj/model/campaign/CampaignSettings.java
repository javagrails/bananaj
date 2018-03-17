/**
 * @author alexanderweiss
 * @date 06.12.2015
 */
package com.github.alexanderwe.bananaj.model.campaign;

import java.net.URL;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.json.JSONObject;

import com.github.alexanderwe.bananaj.connection.MailChimpConnection;
import com.github.alexanderwe.bananaj.exceptions.CampaignSettingsException;
import com.github.alexanderwe.bananaj.exceptions.EmailException;
import com.github.alexanderwe.bananaj.helper.EmailValidator;

/**
 * Class for representing settings for a campaign, including subject, from name, reply-to address, and more.
 * @author alexanderweiss
 *
 */
public class CampaignSettings {


	private MailChimpConnection connection;

	@JsonProperty
	private String subject_line;
	@JsonProperty
	private String preview_text;
	@JsonProperty
	private String title;
	@JsonProperty
	private String from_name;
	@JsonProperty
	private String reply_to;
	@JsonProperty
	private boolean use_conversation;
	@JsonProperty
	private String to_name;
	@JsonProperty
	private String folder_id;
	@JsonProperty
	private boolean authenticate;
	@JsonProperty
	private Boolean auto_footer;
	@JsonProperty
	private Boolean inline_css;
	@JsonProperty
	private Boolean auto_tweet;
	@JsonProperty
	private List<String> auto_fb_post;
	@JsonProperty
	private Boolean fb_comments;
	@JsonProperty
	private int template_id;
	@JsonProperty
	private boolean timewarp;
	@JsonProperty
	private boolean drag_and_drop;


	private CampaignSettings() {

	}

	private CampaignSettings(Builder b) throws CampaignSettingsException{

		if(b.subject_line == null){
			throw new CampaignSettingsException("You need to provide a 'subject line' for a campaign setting");
		} else {
			this.subject_line = b.subject_line;
		}

		this.preview_text = b.preview_text;

		if(b.title == null){
			throw new CampaignSettingsException("You need to provide a 'title' for a campaign setting");
		} else {
			this.title = b.title;
		}

		
		if(b.from_name == null){
			throw new CampaignSettingsException("You need to provide a 'From name' for a campaign setting");
		} else {
			this.from_name = b.from_name;
		}

		if(b.reply_to == null){
			throw new CampaignSettingsException("You need to provide a 'Reply to email address' for a campaign setting");
		} else {
			this.reply_to = b.reply_to;
		}

		this.use_conversation = b.use_conversation;
		this.to_name = b.to_name;
		this.folder_id = b.folder_id;
		this.authenticate = b.authenticate;
		this.auto_footer = b.auto_footer;
		this.inline_css = b.inline_css;
		this.auto_tweet = b.auto_tweet;
		this.auto_fb_post = b.auto_fb_post;
		this.fb_comments = b.fb_comments;
		this.template_id = b.template_id;
		this.timewarp = b.timewarp;
		this.drag_and_drop = b.drag_and_drop;
		this.connection = b.connection;
	}

	private String getString(JSONObject settings, String key) {
		if (settings.has(key)) {
			return settings.getString(key);
		}
		return null;
	}
	
	private Boolean getBoolean(JSONObject settings, String key) {
		if (settings.has(key)) {
			return settings.getBoolean(key);
		}
		return null;
	}

	/**
	 * CampaignSettings builder pattern. 
	 *
	 */
	public static class Builder {
		private String subject_line;
		private String preview_text;
		private String title;
		private String to_name;
		private String from_name;
		private String reply_to;
		private int template_id;
		private Boolean auto_footer;
		private Boolean use_conversation;
		private Boolean authenticate;
		private Boolean fb_comments;
		private Boolean inline_css;
		private Boolean auto_tweet;
		private List<String> auto_fb_post;
		private String folder_id;
		private Boolean timewarp;
		private Boolean drag_and_drop;
		private MailChimpConnection connection;

		public Builder (MailChimpConnection connection) {
			this.connection = connection;
		}
		
		public Builder (CampaignSettings settings) {
			this.subject_line = settings.subject_line;
			this.title = settings.title;
			this.to_name = settings.to_name;
			this.from_name = settings.from_name;
			this.reply_to = settings.reply_to;
			this.template_id = settings.template_id;
			this.auto_footer = settings.auto_footer;
			this.use_conversation = settings.use_conversation;
			this.authenticate = settings.authenticate;
			this.auto_tweet = settings.auto_tweet;
			this.fb_comments = settings.fb_comments;
			this.inline_css = settings.inline_css;
			this.folder_id = settings.folder_id;
			this.timewarp = settings.timewarp;
			this.drag_and_drop = settings.drag_and_drop;
			this.connection = settings.connection;
		}

		public Builder () {
			
		}
		
		public Builder subject_line(String subject_line) {
			this.subject_line = subject_line;
			return this;
		}

		public Builder title(String title) {
			this.title = title;
			return this;
		}

		public Builder to_name(String to_name) {
			this.to_name = to_name;
			return this;
		}

		public Builder from_name(String from_name) {
			this.from_name = from_name;
			return this;
		}

		public Builder reply_to(String reply_to) {
			this.reply_to = reply_to;
			return this;
		}

		public Builder template_id(int template_id) {
			this.template_id = template_id;
			return this;
		}

		public Builder auto_footer(Boolean auto_footer) {
			this.auto_footer = auto_footer;
			return this;
		}
		
		public Builder use_conversation(Boolean use_conversation) {
			this.use_conversation = use_conversation;
			return this;
		}
		
		public Builder authenticate(Boolean authenticate) {
			this.authenticate = authenticate;
			return this;
		}

		
		public Builder auto_tweet(Boolean auto_tweet) {
			this.auto_tweet = auto_tweet;
			return this;
		}
		
		public Builder fb_comments(Boolean fb_comments) {
			this.fb_comments = fb_comments;
			return this;
		}

		
		public Builder inline_css(Boolean inline_css) {
			this.inline_css = inline_css;
			return this;
		}
		
		public Builder folder_id(String folder_id) {
			this.folder_id = folder_id;
			return this;
		}

		public Builder preview_text(String preview_text) {
			this.preview_text = preview_text;
			return this;
		}


		public Builder auto_fb_post(List<String> auto_fb_post) {
			this.auto_fb_post = auto_fb_post;
			return this;
		}

		public Builder drag_and_drop(Boolean drag_and_drop) {
			this.drag_and_drop = drag_and_drop;
			return this;
		}

		public Builder timewarp(Boolean timewarp) {
			this.timewarp = timewarp;
			return this;
		}

		public Builder connection(MailChimpConnection connection) {
			this.connection = connection;
			return this;
		}

		
		public CampaignSettings build() {
			try {
				return new CampaignSettings(this);
			} catch (CampaignSettingsException e) {
				e.printStackTrace();
			}
			return null;
		}
	}

	public MailChimpConnection getConnection() {
		return connection;
	}

	public void setConnection(MailChimpConnection connection) {
		this.connection = connection;
	}

	public String getSubject_line() {
		return subject_line;
	}

	public void setSubject_line(String subject_line) {
		this.subject_line = subject_line;
	}

	public String getPreview_text() {
		return preview_text;
	}

	public void setPreview_text(String preview_text) {
		this.preview_text = preview_text;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFrom_name() {
		return from_name;
	}

	public void setFrom_name(String from_name) {
		this.from_name = from_name;
	}

	public String getReply_to() {
		return reply_to;
	}

	public void setReply_to(String reply_to) {
		this.reply_to = reply_to;
	}

	public boolean isUse_conversation() {
		return use_conversation;
	}

	public void setUse_conversation(boolean use_conversation) {
		this.use_conversation = use_conversation;
	}

	public String getTo_name() {
		return to_name;
	}

	public void setTo_name(String to_name) {
		this.to_name = to_name;
	}

	public String getFolder_id() {
		return folder_id;
	}

	public void setFolder_id(String folder_id) {
		this.folder_id = folder_id;
	}

	public boolean isAuthenticate() {
		return authenticate;
	}

	public void setAuthenticate(boolean authenticate) {
		this.authenticate = authenticate;
	}

	public Boolean getAuto_footer() {
		return auto_footer;
	}

	public void setAuto_footer(Boolean auto_footer) {
		this.auto_footer = auto_footer;
	}

	public Boolean getInline_css() {
		return inline_css;
	}

	public void setInline_css(Boolean inline_css) {
		this.inline_css = inline_css;
	}

	public Boolean getAuto_tweet() {
		return auto_tweet;
	}

	public void setAuto_tweet(Boolean auto_tweet) {
		this.auto_tweet = auto_tweet;
	}

	public List<String> getAuto_fb_post() {
		return auto_fb_post;
	}

	public void setAuto_fb_post(List<String> auto_fb_post) {
		this.auto_fb_post = auto_fb_post;
	}

	public Boolean getFb_comments() {
		return fb_comments;
	}

	public void setFb_comments(Boolean fb_comments) {
		this.fb_comments = fb_comments;
	}

	public int getTemplate_id() {
		return template_id;
	}

	public void setTemplate_id(int template_id) {
		this.template_id = template_id;
	}

	public boolean isTimewarp() {
		return timewarp;
	}

	public void setTimewarp(boolean timewarp) {
		this.timewarp = timewarp;
	}

	public boolean isDrag_and_drop() {
		return drag_and_drop;
	}

	public void setDrag_and_drop(boolean drag_and_drop) {
		this.drag_and_drop = drag_and_drop;
	}
}
