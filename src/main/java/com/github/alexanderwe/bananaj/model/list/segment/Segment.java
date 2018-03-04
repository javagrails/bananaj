package com.github.alexanderwe.bananaj.model.list.segment;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.alexanderwe.bananaj.connection.MailChimpConnection;
import com.github.alexanderwe.bananaj.exceptions.MailchimpAPIException;
import com.github.alexanderwe.bananaj.exceptions.SegmentException;
import com.github.alexanderwe.bananaj.helper.HTTPHelper;
import com.github.alexanderwe.bananaj.model.Link;
import com.github.alexanderwe.bananaj.model.list.member.Member;
import com.github.alexanderwe.bananaj.model.list.member.Members;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

/**
 * Created by alexanderweiss on 04.02.16.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Segment {

    @JsonProperty
    private String id;
    @JsonProperty
    private String name;
    @JsonProperty
    private int member_count;
    @JsonProperty
    private SegmentType type;
    @JsonProperty
    private LocalDateTime created_at;
    @JsonProperty
    private LocalDateTime updated_at;
    @JsonProperty
    private Options options;
    @JsonProperty
    private String list_id;
    @JsonProperty
    private String[] static_segment;
    @JsonProperty
    private List<Link> _links;

    private MailChimpConnection connection;


    /**
     * Add a member to this segment, only NON STATIC segments allowed
     *
     * @param member
     * @throws Exception
     */
    public void addMember(Member member) throws Exception {
        if (!this.getType().equals(SegmentType.STATIC)) {
            throw new SegmentException();
        }


        HttpResponse<JsonNode> postReponse = Unirest.post(this.connection.getListendpoint() + "/" + this.getId() + "/segments/" + this.getId() + "/members")
                .header("Authorization", this.connection.getApikey())
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(member)
                .asJson();


        if (postReponse.getStatus() / 100 != 2) {
            throw new MailchimpAPIException(postReponse.getBody().getObject());
        }
    }

    /**
     * Get all members in this list
     *
     * @param count  x first members
     * @param offset skip x first members
     * @return
     * @throws Exception
     */
    public Members getMembers(int count, int offset) throws Exception {

        String url;
        if (count != 0) {
            url = this.connection.getListendpoint() + "/" + this.getList_id() + "/segments/" + "/members?count=" + count + "&offset=" + offset;
        } else {
            url = this.connection.getListendpoint() + "/" + this.getList_id() + "/members?count=" + this.getMember_count() + "&offset=" + offset;
        }

        HttpResponse<Members> membersHttpResponse = Unirest.get(url)
                .header("Authorization", this.connection.getApikey())
                .asObject(Members.class);

        Members members = membersHttpResponse.getBody();

        members.getMembers().forEach(member -> {
            member.setConnection(this.getConnection());
        });


        return membersHttpResponse.getBody();
    }

    /**
     * Remove a member from this segment, only STATIC segments allowed
     *
     * @param member_id
     * @throws Exception
     */
    public void removeMember(String member_id) throws Exception {
        if (!this.getType().equals(SegmentType.STATIC)) {
            throw new SegmentException();
        }
        HTTPHelper.delete(connection.getListendpoint() + "/" + this.getList_id() + "/segments/" + this.getId() + "/members/" + member_id, this.connection.getApikey());
    }

    /**
     * Update a segment with a local segment
     *
     * @param updatedSegment
     * @throws Exception
     */
    public void update(Segment updatedSegment) throws Exception {
        HTTPHelper.patch(connection.getListendpoint() + "/" + this.getList_id() + "/segments/" + this.getId(), updatedSegment, this.connection.getApikey());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMember_count() {
        return member_count;
    }

    public void setMember_count(int member_count) {
        this.member_count = member_count;
    }

    public SegmentType getType() {
        return type;
    }

    public void setType(SegmentType type) {
        this.type = type;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        OffsetDateTime offsetDateTime = OffsetDateTime.parse(created_at);
        this.created_at = offsetDateTime.toLocalDateTime();
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        OffsetDateTime offsetDateTime = OffsetDateTime.parse(updated_at);
        this.updated_at = offsetDateTime.toLocalDateTime();
    }

    public Options getOptions() {
        return options;
    }

    public void setOptions(Options options) {
        this.options = options;
    }

    public String getList_id() {
        return list_id;
    }

    public void setList_id(String list_id) {
        this.list_id = list_id;
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

    public String[] getStatic_segment() {
        return static_segment;
    }

    public void setStatic_segment(String[] static_segment) {
        this.static_segment = static_segment;
    }
}
