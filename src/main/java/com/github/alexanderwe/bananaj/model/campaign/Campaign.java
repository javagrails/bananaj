/**
 * @author alexanderweiss
 * @date 19.11.2015
 */
package com.github.alexanderwe.bananaj.model.campaign;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.alexanderwe.bananaj.connection.MailChimpConnection;
import com.github.alexanderwe.bananaj.exceptions.MailchimpAPIException;
import com.github.alexanderwe.bananaj.helper.HTTPHelper;
import com.github.alexanderwe.bananaj.model.Link;
import com.github.alexanderwe.bananaj.model.MailchimpObject;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

/**
 * Class for representing a mailchimp campaign
 *
 * @author alexanderweiss
 * TODO: add missing functions and implement JsonIgnore objects
 */
public class Campaign extends MailchimpObject {

    private MailChimpConnection connection;

    @JsonProperty
    private String id;
    @JsonProperty
    private int web_id;
    @JsonProperty
    private CampaignType type;
    @JsonProperty
    private LocalDateTime create_time;
    @JsonProperty
    private String archive_url;
    @JsonProperty
    private String long_archive_url;
    @JsonProperty
    private String status;
    @JsonProperty
    private int emails_sent;
    @JsonProperty
    private LocalDateTime send_time;
    @JsonProperty
    private ContentType content_type;
    @JsonProperty
    private boolean needs_block_refresh;
    @JsonIgnore
    private List<Object> recipients;
    @JsonProperty
    private CampaignSettings settings;
    @JsonIgnore
    private Object variate_settings;
    @JsonIgnore
    private Object tracking;
    @JsonIgnore
    private Object rss_opts;
    @JsonIgnore
    private Object ab_split_opts;
    @JsonIgnore
    private Object social_card;
    @JsonIgnore
    private Object report_summary;
    @JsonIgnore
    private Object delivery_status;
    @JsonProperty
    List<Link> _links;


    /**
     * Get the the HTML and plain-text content for a campaign.
     * @return
     * @throws UnirestException
     * @throws MailchimpAPIException
     */
    public Content getContent() throws UnirestException, MailchimpAPIException {
        return HTTPHelper.get(connection.getCampaignendpoint() + "/" + this.getId() + "/content", this.connection.getApikey(), Content.class).getBody();
    }

    /**
     * Review the send checklist for a campaign, and resolve any issues before sending.
     * @return
     * @throws UnirestException
     * @throws MailchimpAPIException
     */
    public SendChecklist getSendChecklist() throws UnirestException, MailchimpAPIException {
        return HTTPHelper.get(connection.getCampaignendpoint() + "/" + this.getId() + "/send-checklist", this.connection.getApikey(), SendChecklist.class).getBody();
    }

    /**
     * Retrieves all feedback messages
     * @return
     * @throws UnirestException
     * @throws MailchimpAPIException
     */
    public Feedback getFeedback() throws UnirestException, MailchimpAPIException {
        return HTTPHelper.get(connection.getCampaignendpoint() + "/" + this.getId() + "/feedback", this.connection.getApikey(), Feedback.class).getBody();
    }

    /**
     * Retrieves a single feedback message
     * @param feedback_id
     * @return
     * @throws UnirestException
     * @throws MailchimpAPIException
     */
    public Feedback.FeedbackMessage getFeedbackMessage(int feedback_id) throws UnirestException, MailchimpAPIException {
        return HTTPHelper.get(connection.getCampaignendpoint() + "/" + this.getId() + "/feedback/" + feedback_id, this.connection.getApikey(), Feedback.FeedbackMessage.class).getBody();
    }

    /**
     * Adds a feedback message
     * @param feedbackMessage
     * @throws UnirestException
     * @throws MailchimpAPIException
     */
    public void addFeedbackMessage(String feedbackMessage) throws UnirestException, MailchimpAPIException {
        Feedback.FeedbackMessage feedbackMessageObj = new Feedback.FeedbackMessage();
        feedbackMessageObj.setMessage(feedbackMessage);

        HTTPHelper.post(connection.getCampaignendpoint() + "/" + this.getId() + "/feedback", feedbackMessageObj, this.connection.getApikey());
    }

    /**
     * Changes the feeback message content
     * @param feedbackMessage
     * @param feedback_id
     * @throws UnirestException
     * @throws MailchimpAPIException
     */
    public void changeeedbackMessage(String feedbackMessage, int feedback_id) throws UnirestException, MailchimpAPIException {
        Feedback.FeedbackMessage feedbackMessageObj = new Feedback.FeedbackMessage();
        feedbackMessageObj.setMessage(feedbackMessage);

        HTTPHelper.post(connection.getCampaignendpoint() + "/" + this.getId() + "/feedback/" + feedback_id, feedbackMessageObj, this.connection.getApikey());
    }

    /**
     * Deletes an feedback message
     * @param feedback_id
     * @throws UnirestException
     * @throws MailchimpAPIException
     */
    public void deleteFeedbackMessage(int feedback_id) throws UnirestException, MailchimpAPIException {
        HTTPHelper.delete(connection.getCampaignendpoint() + "/" + this.getId() + "/feedback/" + feedback_id, this.connection.getApikey());
    }


    /**
     * Stops the sending of your campaign
     * (!Only included in mailchimp pro)
     */
    public void cancelSend() throws UnirestException, MailchimpAPIException {
        HTTPHelper.post(connection.getCampaignendpoint() + "/" + this.getId() + "/actions/cancel-send", null, connection.getApikey());
    }

    /**
     * Pause an RSS-Driven campaign.
     */
    public void pause() throws UnirestException, MailchimpAPIException {
        HTTPHelper.post(connection.getCampaignendpoint() + "/" + this.getId() + "/actions/pause", null, connection.getApikey());
    }

    /**
     * Replicate a campaign in saved or send status.
     */
    public void replicate() throws UnirestException, MailchimpAPIException {
        HTTPHelper.post(connection.getCampaignendpoint() + "/" + this.getId() + "/actions/replicate", null, connection.getApikey());
    }

    /**
     * Resume an RSS-Driven campaign.
     */
    public void resume() throws UnirestException, MailchimpAPIException {
        HTTPHelper.post(connection.getCampaignendpoint() + "/" + this.getId() + "/actions/resume", null, connection.getApikey());
    }

    /**
     * Schedule a campaign for delivery. If you’re using Multivariate Campaigns to test send times or sending RSS Campaigns, use the send action instead.
     *
     * @param schedule_time The date and time in UTC to schedule the campaign for delivery. Campaigns may only be scheduled to send on the quarter-hour (:00, :15, :30, :45).
     */
    public void schedule(String schedule_time) throws UnirestException, MailchimpAPIException {

        JSONObject data = new JSONObject();
        data.put("schedule_time", schedule_time);

        HTTPHelper.post(connection.getCampaignendpoint() + "/" + this.getId() + "/actions/schedule", data, connection.getApikey());
    }

    /**
     * Schedule a campaign for delivery. If you’re using Multivariate Campaigns to test send times or sending RSS Campaigns, use the send action instead.
     *
     * @param schedule_time The date and time in UTC to schedule the campaign for delivery. Campaigns may only be scheduled to send on the quarter-hour (:00, :15, :30, :45).
     * @param timewarp      Choose whether the campaign should use Timewarp when sending. Campaigns scheduled with Timewarp are localized based on the recipients’ time zones. For example, a Timewarp campaign with a schedule_time of 13:00 will be sent to each recipient at 1:00pm in their local time. Cannot be set to true for campaigns using Batch Delivery.
     */
    public void schedule(String schedule_time, boolean timewarp) throws UnirestException, MailchimpAPIException {

        JSONObject data = new JSONObject();
        data.put("schedule_time", schedule_time);
        data.put("timewarp", timewarp);

        HTTPHelper.post(connection.getCampaignendpoint() + "/" + this.getId() + "/actions/schedule", data, connection.getApikey());
    }

    /**
     * Schedule a campaign for delivery. If you’re using Multivariate Campaigns to test send times or sending RSS Campaigns, use the send action instead.
     *
     * @param schedule_time The date and time in UTC to schedule the campaign for delivery. Campaigns may only be scheduled to send on the quarter-hour (:00, :15, :30, :45).
     * @param batch_delay   The delay, in minutes, between batches.
     * @param batch_count   The number of batches for the campaign send.
     */
    public void schedule(String schedule_time, int batch_delay, int batch_count) throws UnirestException, MailchimpAPIException {

        JSONObject data = new JSONObject();
        data.put("schedule_time", schedule_time);

        JSONObject batch = new JSONObject();
        batch.put("batch_delay", batch_delay);
        batch.put("batch_count", batch_count);

        data.put("batch_delivery", batch);

        HTTPHelper.post(connection.getCampaignendpoint() + "/" + this.getId() + "/actions/schedule", data, connection.getApikey());
    }

    /**
     * Send a MailChimp campaign. For RSS Campaigns, the campaign will send according to its schedule. All other campaigns will send immediately.
     */
    public void send() throws UnirestException, MailchimpAPIException {
        HTTPHelper.post(connection.getCampaignendpoint() + "/" + this.getId() + "/actions/send", null, connection.getApikey());
    }

    /**
     * Unschedule a scheduled campaign that hasn’t started sending.
     */
    public void unschedule() throws UnirestException, MailchimpAPIException {
        HTTPHelper.post(connection.getCampaignendpoint() + "/" + this.getId() + "/actions/unschedule", null, connection.getApikey());
    }


    /**
     * Send the campaign to the mailChimpList members
     *
     * @param emails An array of email addresses to send the test email to.
     * @param type   Choose the type of test email to send.
     */
    public void sendTestEmail(String[] emails, CampaignSendType type) throws UnirestException, MailchimpAPIException {
        JSONObject data = new JSONObject();
        JSONArray testEmails = new JSONArray();
        for (String email : emails) {
            testEmails.put(email);
        }
        data.put("test_emails", testEmails);
        data.put("send_type", type.toString());

        HTTPHelper.post(connection.getCampaignendpoint() + "/" + this.getId() + "/actions/test", data, connection.getApikey());
    }

    public MailChimpConnection getConnection() {
        return connection;
    }

    public void setConnection(MailChimpConnection connection) {
        this.connection = connection;
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getWeb_id() {
        return web_id;
    }

    public void setWeb_id(int web_id) {
        this.web_id = web_id;
    }

    public CampaignType getType() {
        return type;
    }

    public void setType(CampaignType type) {
        this.type = type;
    }

    public LocalDateTime getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        OffsetDateTime offsetDateTime = OffsetDateTime.parse(create_time);
        this.create_time = offsetDateTime.toLocalDateTime();
    }

    public String getArchive_url() {
        return archive_url;
    }

    public void setArchive_url(String archive_url) {
        this.archive_url = archive_url;
    }

    public String getLong_archive_url() {
        return long_archive_url;
    }

    public void setLong_archive_url(String long_archive_url) {
        this.long_archive_url = long_archive_url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getEmails_sent() {
        return emails_sent;
    }

    public void setEmails_sent(int emails_sent) {
        this.emails_sent = emails_sent;
    }

    public LocalDateTime getSend_time() {
        return send_time;
    }

    public void setSend_time(String send_time) {
        if (!send_time.isEmpty()){
            OffsetDateTime offsetDateTime = OffsetDateTime.parse(send_time);
            this.send_time =  offsetDateTime.toLocalDateTime();
        } else {
            this.send_time = null;
        }

    }

    public ContentType getContent_type() {
        return content_type;
    }

    public void setContent_type(ContentType content_type) {
        this.content_type = content_type;
    }

    public boolean isNeeds_block_refresh() {
        return needs_block_refresh;
    }

    public void setNeeds_block_refresh(boolean needs_block_refresh) {
        this.needs_block_refresh = needs_block_refresh;
    }

    public List<Object> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<Object> recipients) {
        this.recipients = recipients;
    }

    public CampaignSettings getSettings() {
        return settings;
    }

    public void setSettings(CampaignSettings settings) {
        this.settings = settings;
    }

    public Object getVariate_settings() {
        return variate_settings;
    }

    public void setVariate_settings(Object variate_settings) {
        this.variate_settings = variate_settings;
    }

    public Object getTracking() {
        return tracking;
    }

    public void setTracking(Object tracking) {
        this.tracking = tracking;
    }

    public Object getRss_opts() {
        return rss_opts;
    }

    public void setRss_opts(Object rss_opts) {
        this.rss_opts = rss_opts;
    }

    public Object getAb_split_opts() {
        return ab_split_opts;
    }

    public void setAb_split_opts(Object ab_split_opts) {
        this.ab_split_opts = ab_split_opts;
    }

    public Object getSocial_card() {
        return social_card;
    }

    public void setSocial_card(Object social_card) {
        this.social_card = social_card;
    }

    public Object getReport_summary() {
        return report_summary;
    }

    public void setReport_summary(Object report_summary) {
        this.report_summary = report_summary;
    }

    public Object getDelivery_status() {
        return delivery_status;
    }

    public void setDelivery_status(Object delivery_status) {
        this.delivery_status = delivery_status;
    }

    public List<Link> get_links() {
        return _links;
    }

    public void set_links(List<Link> _links) {
        this._links = _links;
    }
}
