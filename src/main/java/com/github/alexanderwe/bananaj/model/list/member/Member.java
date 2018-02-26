package com.github.alexanderwe.bananaj.model.list.member;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.alexanderwe.bananaj.connection.MailChimpConnection;
import com.github.alexanderwe.bananaj.exceptions.MailchimpAPIException;
import com.github.alexanderwe.bananaj.model.Link;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.LinkedHashMap;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Member {

    //TODO: Add merge fields

    private MailChimpConnection connection;

    @JsonProperty
    String id;
    @JsonProperty
    String email_address;
    @JsonProperty
    String unique_email_id;
    @JsonProperty
    String email_type; //TODO: ENUM
    @JsonProperty
    MemberStatus status;
    @JsonProperty
    String unsubscribe_reason;
    @JsonProperty
    LinkedHashMap<String, String> merge_fields;
    @JsonProperty
    Stats stats;
    @JsonProperty
    Object interests;
    @JsonProperty
    String ip_signup;
    @JsonProperty
    String timestamp_signup;
    @JsonProperty
    String ip_opt;
    @JsonProperty
    String timestamp_opt;
    @JsonProperty
    String member_rating;
    @JsonProperty
    LocalDateTime last_changed;
    @JsonProperty
    String language;
    @JsonProperty
    boolean vip;
    @JsonProperty
    String email_client;
    @JsonProperty
    Location location;
    @JsonProperty
    Note last_note;
    @JsonProperty
    String list_id;
    @JsonProperty
    Link[] _links;

    /**
     * Update the mailChimpList of this member
     * @param list_id
     * @throws Exception
     */
    public void changeList(String list_id) throws Exception{
        Member member = new Member();
        member.setList_id(list_id);

        HttpResponse<JsonNode> postReponse = Unirest.patch(this.connection.getListendpoint()+"/"+this.getList_id() + "/members/"+this.getId())
                .header("Authorization", this.connection.getApikey())
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(member)
                .asJson();

        if (postReponse.getStatus() / 100 != 2) {
            throw new MailchimpAPIException(postReponse.getBody().getObject());
        }
        this.list_id = list_id;
    }


    /**
     * Update the mailChimpList of this member
     * @param email_address
     * @throws Exception
     */
    public void changeEmailAddress(String email_address) throws Exception{
        Member member = new Member();
        member.setEmail_address(email_address);

        HttpResponse<JsonNode> postReponse = Unirest.patch(this.connection.getListendpoint()+"/"+this.getList_id() + "/members/"+this.getId())
                .header("Authorization", this.connection.getApikey())
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(member)
                .asJson();

        if (postReponse.getStatus() / 100 != 2) {
            throw new MailchimpAPIException(postReponse.getBody().getObject());
        }
        this.email_address = email_address;
    }


    /**
     * Update the mailChimpList of this member
     * @param memberStatus
     * @throws Exception
     */
    public void changeMemberStatus(MemberStatus memberStatus) throws Exception{
        Member member = new Member();
        member.setStatus(memberStatus);

        HttpResponse<JsonNode> postReponse = Unirest.patch(this.connection.getListendpoint()+"/"+this.getList_id() + "/members/"+this.getId())
                .header("Authorization", this.connection.getApikey())
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(member)
                .asJson();

        if (postReponse.getStatus() / 100 != 2) {
            throw new MailchimpAPIException(postReponse.getBody().getObject());
        }
        this.status = memberStatus;
    }


    public Activities getActivities() throws UnirestException {
        System.out.println("List id:" + this.getList_id());
        System.out.println("id:" + this.getId());
        HttpResponse<Activities> memberActivitiesHttpResponse = Unirest.get(this.connection.getListendpoint() + "/" + this.getList_id() + "/members/"+this.getId()+"/activity")
                .header("Authorization", this.connection.getApikey())
                .asObject(Activities.class);
        return memberActivitiesHttpResponse.getBody();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail_address() {
        return email_address;
    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }

    public String getUnique_email_id() {
        return unique_email_id;
    }

    public void setUnique_email_id(String unique_email_id) {
        this.unique_email_id = unique_email_id;
    }

    public String getEmail_type() {
        return email_type;
    }

    public void setEmail_type(String email_type) {
        this.email_type = email_type;
    }

    public MemberStatus getStatus() {
        return status;
    }

    public void setStatus(MemberStatus status) {
        this.status = status;
    }

    public String getUnsubscribe_reason() {
        return unsubscribe_reason;
    }

    public void setUnsubscribe_reason(String unsubscribe_reason) {
        this.unsubscribe_reason = unsubscribe_reason;
    }

    public  LinkedHashMap<String, String> getMerge_fields() {
        return merge_fields;
    }

    public void setMerge_fields( LinkedHashMap<String, String> merge_fields) {
        this.merge_fields = merge_fields;
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }


    public Object getInterests() {
        return interests;
    }

    public void setInterests(Object interests) {
        this.interests = interests;
    }

    public String getIp_signup() {
        return ip_signup;
    }

    public void setIp_signup(String ip_signup) {
        this.ip_signup = ip_signup;
    }

    public String getTimestamp_signup() {
        return timestamp_signup;
    }

    public void setTimestamp_signup(String timestamp_signup) {
        this.timestamp_signup = timestamp_signup;
    }

    public String getIp_opt() {
        return ip_opt;
    }

    public void setIp_opt(String ip_opt) {
        this.ip_opt = ip_opt;
    }


    public String getTimestamp_opt() {
        return timestamp_opt;
    }

    public void setTimestamp_opt(String timestamp_opt) {
        this.timestamp_opt = timestamp_opt;
    }

    public String getMember_rating() {
        return member_rating;
    }

    public void setMember_rating(String member_rating) {
        this.member_rating = member_rating;
    }

    public LocalDateTime getLast_changed() {
        return last_changed;
    }

    public void setLast_changed(String last_changed) {
        OffsetDateTime offsetDateTime = OffsetDateTime.parse(last_changed);
        this.last_changed = offsetDateTime.toLocalDateTime();
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public boolean isVip() {
        return vip;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }

    public String getEmail_client() {
        return email_client;
    }

    public void setEmail_client(String email_client) {
        this.email_client = email_client;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Note getLast_note() {
        return last_note;
    }

    public void setLast_note(Note last_note) {
        this.last_note = last_note;
    }

    public String getList_id() {
        return list_id;
    }

    public void setList_id(String list_id) {
        this.list_id = list_id;
    }

    public Link[] get_links() {
        return _links;
    }

    public void set_links(Link[] _links) {
        this._links = _links;
    }

    public void setConnection(MailChimpConnection connection) {
        this.connection = connection;
    }
}
