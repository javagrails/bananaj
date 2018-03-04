/**
 * @author alexanderweiss
 * @date 19.11.2015
 */
package com.github.alexanderwe.bananaj.model.report;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.alexanderwe.bananaj.model.Link;
import com.github.alexanderwe.bananaj.model.campaign.Bounces;
import com.github.alexanderwe.bananaj.model.campaign.CampaignType;
import com.github.alexanderwe.bananaj.model.list.Stats;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Object for representing a report of a campaign
 * @author alexanderweiss
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Report{

	@JsonProperty
	private String id;
	@JsonProperty
	private String campaign_title;
	@JsonProperty
	private CampaignType type;
	@JsonProperty
	private String list_id;
	@JsonProperty
	private String list_name;
	@JsonProperty
	private String subject_line;
	@JsonProperty
	private String preview_text;
	@JsonProperty
	private int emails_sent;
	@JsonProperty
	private int abuse_reports;
	@JsonProperty
	private int unsubscribed;
	@JsonProperty
	private LocalDateTime send_time;
	@JsonProperty
	private LocalDateTime rss_last_send;
	@JsonProperty
	private Bounces bounces;
	@JsonProperty
	private Forwards forwards;
	@JsonProperty
	private Opens opens;
	@JsonProperty
	private Clicks clicks;
	@JsonProperty
	private FacebookLikes facebook_likes;
	@JsonProperty
	private IndustryStats industry_stats;
	@JsonProperty
	private Stats list_stats;
	@JsonProperty
	private ABSplit ab_split;
	@JsonProperty
	private List<TimeWarp> timewarp;
	@JsonProperty
	private List<Timeserie> timeseries;
	@JsonProperty
	private ShareReport shareReport;
	@JsonProperty
	private ECommerce ecommerce;
	@JsonProperty
	private DeliveryStatus delivery_status;
	@JsonProperty
	private int total_items;
	@JsonProperty
	Link[] _links;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCampaign_title() {
		return campaign_title;
	}

	public void setCampaign_title(String campaign_title) {
		this.campaign_title = campaign_title;
	}

	public CampaignType getType() {
		return type;
	}

	public void setType(CampaignType type) {
		this.type = type;
	}

	public String getList_id() {
		return list_id;
	}

	public void setList_id(String list_id) {
		this.list_id = list_id;
	}

	public String getList_name() {
		return list_name;
	}

	public void setList_name(String list_name) {
		this.list_name = list_name;
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

	public int getEmails_sent() {
		return emails_sent;
	}

	public void setEmails_sent(int emails_sent) {
		this.emails_sent = emails_sent;
	}

	public int getAbuse_reports() {
		return abuse_reports;
	}

	public void setAbuse_reports(int abuse_reports) {
		this.abuse_reports = abuse_reports;
	}

	public int getUnsubscribed() {
		return unsubscribed;
	}

	public void setUnsubscribed(int unsubscribed) {
		this.unsubscribed = unsubscribed;
	}

	public LocalDateTime getSend_time() {
		return send_time;
	}

	public void setSend_time(LocalDateTime send_time) {
		this.send_time = send_time;
	}

	public LocalDateTime getRss_last_send() {
		return rss_last_send;
	}

	public void setRss_last_send(LocalDateTime rss_last_send) {
		this.rss_last_send = rss_last_send;
	}

	public Bounces getBounces() {
		return bounces;
	}

	public void setBounces(Bounces bounces) {
		this.bounces = bounces;
	}

	public Forwards getForwards() {
		return forwards;
	}

	public void setForwards(Forwards forwards) {
		this.forwards = forwards;
	}

	public Opens getOpens() {
		return opens;
	}

	public void setOpens(Opens opens) {
		this.opens = opens;
	}

	public Clicks getClicks() {
		return clicks;
	}

	public void setClicks(Clicks clicks) {
		this.clicks = clicks;
	}

	public FacebookLikes getFacebook_likes() {
		return facebook_likes;
	}

	public void setFacebook_likes(FacebookLikes facebook_likes) {
		this.facebook_likes = facebook_likes;
	}

	public IndustryStats getIndustry_stats() {
		return industry_stats;
	}

	public void setIndustry_stats(IndustryStats industry_stats) {
		this.industry_stats = industry_stats;
	}

	public Stats getList_stats() {
		return list_stats;
	}

	public void setList_stats(Stats list_stats) {
		this.list_stats = list_stats;
	}

	public ABSplit getAb_split() {
		return ab_split;
	}

	public void setAb_split(ABSplit ab_split) {
		this.ab_split = ab_split;
	}

	public List<TimeWarp> getTimewarp() {
		return timewarp;
	}

	public void setTimewarp(List<TimeWarp> timewarp) {
		this.timewarp = timewarp;
	}

	public List<Timeserie> getTimeseries() {
		return timeseries;
	}

	public void setTimeseries(List<Timeserie> timeseries) {
		this.timeseries = timeseries;
	}

	public ShareReport getShareReport() {
		return shareReport;
	}

	public void setShareReport(ShareReport shareReport) {
		this.shareReport = shareReport;
	}

	public ECommerce getEcommerce() {
		return ecommerce;
	}

	public void setEcommerce(ECommerce ecommerce) {
		this.ecommerce = ecommerce;
	}

	public DeliveryStatus getDelivery_status() {
		return delivery_status;
	}

	public void setDelivery_status(DeliveryStatus delivery_status) {
		this.delivery_status = delivery_status;
	}

	public int getTotal_items() {
		return total_items;
	}

	public void setTotal_items(int total_items) {
		this.total_items = total_items;
	}

	public Link[] get_links() {
		return _links;
	}

	public void set_links(Link[] _links) {
		this._links = _links;
	}
}
