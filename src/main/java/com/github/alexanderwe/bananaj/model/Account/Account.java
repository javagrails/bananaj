package com.github.alexanderwe.bananaj.model.Account;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.alexanderwe.bananaj.connection.MailChimpConnection;
import com.github.alexanderwe.bananaj.model.Link;
import com.github.alexanderwe.bananaj.model.MailchimpObject;
import com.github.alexanderwe.bananaj.model.report.IndustryStats;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

/**
 * Class for representing your mailchimp account
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
 public class Account extends MailchimpObject{

	private String apiKey;
    @JsonProperty
    private String account_id;
    @JsonProperty
    private String login_id;
	@JsonProperty
	private String account_name;
    @JsonProperty
    private String email;
    @JsonProperty
    private String first_name;
    @JsonProperty
    private String last_name;
    @JsonProperty
    private String username;
    @JsonProperty
    private String avatar_url;
    @JsonProperty
    private String role;
    @JsonProperty
    private LocalDateTime member_since;
    @JsonProperty
    private String pricing_plan_type;
    @JsonProperty
    private LocalDateTime first_payment;
    @JsonProperty
    private String account_timezone;
    @JsonProperty
    private String account_industry;
    @JsonProperty
    private Contact contact;
    @JsonProperty
    private boolean pro_enabled;
    @JsonProperty
    private LocalDateTime last_login;
    @JsonProperty
    private int total_subscribers;
    @JsonProperty
    private IndustryStats industry_stats;
    @JsonProperty
    private List<Link> _links;

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getLogin_id() {
        return login_id;
    }

    public void setLogin_id(String login_id) {
        this.login_id = login_id;
    }

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDateTime getMember_since() {
        return member_since;
    }

    public void setMember_since(String member_since) {
        OffsetDateTime offsetDateTime = OffsetDateTime.parse(member_since);
        this.member_since = offsetDateTime.toLocalDateTime();
    }

    public String getPricing_plan_type() {
        return pricing_plan_type;
    }

    public void setPricing_plan_type(String pricing_plan_type) {
        this.pricing_plan_type = pricing_plan_type;
    }

    public LocalDateTime getFirst_payment() {
        return first_payment;
    }

    public void setFirst_payment(String first_payment) {
        if (first_payment.isEmpty()){
            this.first_payment = null;
        } else{
            OffsetDateTime offsetDateTime = OffsetDateTime.parse(first_payment);
            this.first_payment = offsetDateTime.toLocalDateTime();
        }

    }

    public String getAccount_timezone() {
        return account_timezone;
    }

    public void setAccount_timezone(String account_timezone) {
        this.account_timezone = account_timezone;
    }

    public String getAccount_industry() {
        return account_industry;
    }

    public void setAccount_industry(String account_industry) {
        this.account_industry = account_industry;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public boolean isPro_enabled() {
        return pro_enabled;
    }

    public void setPro_enabled(boolean pro_enabled) {
        this.pro_enabled = pro_enabled;
    }

    public LocalDateTime getLast_login() {
        return last_login;
    }

    public void setLast_login(String last_login) {
        OffsetDateTime offsetDateTime = OffsetDateTime.parse(last_login);
        this.last_login = offsetDateTime.toLocalDateTime();
    }

    public int getTotal_subscribers() {
        return total_subscribers;
    }

    public void setTotal_subscribers(int total_subscribers) {
        this.total_subscribers = total_subscribers;
    }

    public IndustryStats getIndustry_stats() {
        return industry_stats;
    }

    public void setIndustry_stats(IndustryStats industry_stats) {
        this.industry_stats = industry_stats;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public List<Link> get_links() {
        return _links;
    }

    public void set_links(List<Link> _links) {
        this._links = _links;
    }
}