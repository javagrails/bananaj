package com.github.alexanderwe.bananaj.model.list;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.alexanderwe.bananaj.connection.MailChimpConnection;
import com.github.alexanderwe.bananaj.exceptions.FileFormatException;
import com.github.alexanderwe.bananaj.exceptions.MailchimpAPIException;
import com.github.alexanderwe.bananaj.model.Link;
import com.github.alexanderwe.bananaj.model.campaign.NewCampaignDefaults;
import com.github.alexanderwe.bananaj.model.list.member.MemberStatus;
import com.github.alexanderwe.bananaj.model.list.member.Members;
import com.github.alexanderwe.bananaj.model.list.member.NewMember;
import com.github.alexanderwe.bananaj.model.list.segment.*;
import com.github.alexanderwe.bananaj.utils.DateConverter;
import com.github.alexanderwe.bananaj.utils.FileInspector;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class NewMList {


    private MailChimpConnection connection;

    @JsonProperty
    private String id;

    @JsonProperty
    private String name;

    @JsonProperty
    private int web_id;

    @JsonProperty
    private ListContact contact;

    @JsonProperty
    private String permission_reminder;

    @JsonProperty
    private String use_archive_bar;

    @JsonProperty
    private NewCampaignDefaults campaign_defaults;

    @JsonProperty
    private String notify_on_subscribe;

    @JsonProperty
    private String notify_on_unsubscribe;

    @JsonProperty
    private LocalDateTime date_created;

    @JsonProperty
    private int list_rating;

    @JsonProperty
    private boolean email_type_option;

    @JsonProperty
    private String subscribe_url_short;

    @JsonProperty
    private String subscribe_url_long;

    @JsonProperty
    private String beamer_address;

    @JsonProperty
    private boolean double_optin;

    @JsonProperty
    private String visibility;

    @JsonIgnore
    private Object modules;

    @JsonProperty
    private Stats stats;

    @JsonProperty
    List<Link> _links;



    /**
     * Get all members in this list
     * @param count x first members
     * @param offset skip x first members
     * @return
     * @throws Exception
     */
    public Members getMembers(int count, int offset) throws Exception{

        String url = null;
        if(count != 0){
            url = this.connection.getListendpoint() +"/"+this.getId()+"/members?count="+count+"&offset="+offset;
        } else {
            url = this.connection.getListendpoint() +"/"+this.getId()+"/members?count="+this.getStats().getMember_count()+"&offset="+offset;
        }

        HttpResponse<Members> membersHttpResponse = Unirest.get(url)
                .header("Authorization", this.connection.getApikey())
                .asObject(Members.class);
        return membersHttpResponse.getBody();
    }

    /**
     * Get a single member from list
     * @param memberID
     * @return
     * @throws Exception
     */
    public NewMember getMember(String memberID) throws Exception{

        HttpResponse<NewMember> newMemberHttpResponse = Unirest.get(this.connection.getListendpoint() +"/"+this.getId()+"/members/"+memberID)
                .header("Authorization", this.connection.getApikey())
                .asObject(NewMember.class);
        return newMemberHttpResponse.getBody();
    }

    /**
     * Add a member with the minimum of information
     * @param status
     * @param emailAddress
     */
    public void addMember(MemberStatus status, String emailAddress) throws MailchimpAPIException, UnirestException {

        NewMember newMember = new NewMember();
        newMember.setEmail_address(emailAddress);
        newMember.setStatus(status);

        HttpResponse<JsonNode>  postReponse = Unirest.post(this.connection.getListendpoint()+"/"+this.getId()+"/members")
                .header("Authorization", this.connection.getApikey())
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(newMember)
                .asJson();


        if (postReponse.getStatus()/ 100 != 2) {
            throw new MailchimpAPIException(postReponse.getBody().getObject());
        }

        int updateMemberCount = this.stats.getMember_count();
        this.stats.setMember_count(++updateMemberCount);
    }

    /**
     * Add a member with first and last name
     * @param status
     * @param emailAddress
     * @param merge_fields_values
     * @throws Exception
     * TODO:
     */
    public void addMember(MemberStatus status, String emailAddress, HashMap<String, Object> merge_fields_values) throws Exception{
        URL url = new URL(connection.getListendpoint()+"/"+this.getId()+"/members");

        JSONObject member = new JSONObject();
        JSONObject merge_fields = new JSONObject();

        Iterator it = merge_fields_values.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            it.remove(); // avoids a ConcurrentModificationException
            merge_fields.put(pair.getKey().toString(), pair.getValue());
        }

        member.put("status", status.getStringRepresentation());
        member.put("email_address", emailAddress);
        member.put("merge_fields", merge_fields);
        getConnection().do_Post(new URL(connection.getListendpoint()+"/"+this.getId()+"/members"),member.toString(),connection.getApikey());
        int updateMemberCount = this.stats.getMember_count();
        this.stats.setMember_count(++updateMemberCount);
    }

    //TODO: implement
    public void importMembersFromFile(File file) throws FileFormatException, IOException {
        //TODO fully implement read from xls
        String extension = FileInspector.getInstance().getExtension(file);

        if(extension.equals(".xls")|| extension.equals(".xlsx")){
            Workbook w;
            try {
                w = Workbook.getWorkbook(file);
                // Get the first sheet
                Sheet sheet = w.getSheet(0);
                // Loop over first 10 column and lines

                for (int j = 0; j < sheet.getColumns(); j++) {
                    for (int i = 0; i < sheet.getRows(); i++) {
                        Cell cell = sheet.getCell(j, i);
                        CellType type = cell.getType();
                        if (type == CellType.LABEL) {
                            System.out.println("I got a label "
                                    + cell.getContents());
                        }

                        if (type == CellType.NUMBER) {
                            System.out.println("I got a number "
                                    + cell.getContents());
                        }

                    }
                }
            } catch (BiffException e) {
                e.printStackTrace();
            }

        }
    }


    /**
     * Delete a member from list
     * @param memberID
     * @throws Exception
     */
    public void deleteMember(String memberID) throws MailchimpAPIException, UnirestException {

        HttpResponse<JsonNode>  postReponse = Unirest.delete(this.connection.getListendpoint()+"/"+this.getId()+"/members/"+memberID)
                .header("Authorization", this.connection.getApikey())
                .header("accept", "application/json")
                .asJson();


        if (postReponse.getStatus()/ 100 != 2) {
            throw new MailchimpAPIException(postReponse.getBody().getObject());
        }

        int updateMemberCount = this.stats.getMember_count();
        this.stats.setMember_count(--updateMemberCount);
    }

    /**
     * Get the growth history of this list
     * @return a growth history
     * @throws Exception
     */
    public GrowthHistory getGrowthHistory() throws Exception{

        HttpResponse<GrowthHistory> growthHistoryHttpResponse = Unirest.get(this.connection.getListendpoint() +"/"+this.getId()+"/growth-history")
                .header("Authorization", this.connection.getApikey())
                .asObject(GrowthHistory.class);
            return  growthHistoryHttpResponse.getBody();
    }

    /**
     * Get all segments of this list
     * @return
     * @throws Exception
     */
	public Segments getSegments() throws Exception {
        HttpResponse<Segments> segmentsHttpResponse = Unirest.get(this.connection.getListendpoint() +"/"+this.getId()+"/segments")
                .header("Authorization", this.connection.getApikey())
                .asObject(Segments.class);
        return segmentsHttpResponse.getBody();
    }

    /**
     * Get a specific segment of this list
     * @param segmentID
     * @return
     * @throws Exception
     */
    public Segment getSegment(String segmentID) throws Exception {
        HttpResponse<Segment> segmentHttpResponse = Unirest.get(this.connection.getListendpoint() +"/"+this.getId()+"/growth-history")
                .header("Authorization", this.connection.getApikey())
                .asObject(Segment.class);
        return  segmentHttpResponse.getBody();
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

    public int getWeb_id() {
        return web_id;
    }

    public void setWeb_id(int web_id) {
        this.web_id = web_id;
    }

    public ListContact getContact() {
        return contact;
    }

    public void setContact(ListContact contact) {
        this.contact = contact;
    }

    public String getPermission_reminder() {
        return permission_reminder;
    }

    public void setPermission_reminder(String permission_reminder) {
        this.permission_reminder = permission_reminder;
    }

    public String getUse_archive_bar() {
        return use_archive_bar;
    }

    public void setUse_archive_bar(String use_archive_bar) {
        this.use_archive_bar = use_archive_bar;
    }

    public NewCampaignDefaults getCampaign_defaults() {
        return campaign_defaults;
    }

    public void setCampaign_defaults(NewCampaignDefaults campaign_defaults) {
        this.campaign_defaults = campaign_defaults;
    }

    public String getNotify_onsubscribe() {
        return notify_on_subscribe;
    }

    public void setNotify_onsubscribe(String notify_ons_ubscribe) {
        this.notify_on_subscribe = notify_on_subscribe;
    }

    public String getNotifiy_on_unsubscribe() {
        return notify_on_unsubscribe;
    }

    public void setNotifiy_on_unsubscribe(String notifiy_on_unsubscribe) {
        this.notify_on_unsubscribe = notifiy_on_unsubscribe;
    }

    public LocalDateTime getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        OffsetDateTime offsetDateTime = OffsetDateTime.parse(date_created);
        this.date_created = offsetDateTime.toLocalDateTime();
    }

    public int getList_rating() {
        return list_rating;
    }

    public void setList_rating(int list_rating) {
        this.list_rating = list_rating;
    }

    public boolean isEmail_type_option() {
        return email_type_option;
    }

    public void setEmail_type_option(boolean email_type_option) {
        this.email_type_option = email_type_option;
    }

    public String getSubscribe_url_short() {
        return subscribe_url_short;
    }

    public void setSubscribe_url_short(String subscribe_url_short) {
        this.subscribe_url_short = subscribe_url_short;
    }

    public String getSubscribe_url_long() {
        return subscribe_url_long;
    }

    public void setSubscribe_url_long(String subscribe_url_long) {
        this.subscribe_url_long = subscribe_url_long;
    }

    public String getBeamer_address() {
        return beamer_address;
    }

    public void setBeamer_address(String beamer_address) {
        this.beamer_address = beamer_address;
    }

    public boolean isDouble_optin() {
        return double_optin;
    }

    public void setDouble_optin(boolean double_optin) {
        this.double_optin = double_optin;
    }


    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public Object getModules() {
        return modules;
    }

    public void setModules(Object modules) {
        this.modules = modules;
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public List<Link> get_links() {
        return _links;
    }

    public void set_links(List<Link> _links) {
        this._links = _links;
    }

    public void setConnection(MailChimpConnection connection) {
        this.connection = connection;
    }

    public MailChimpConnection getConnection() {
        return this.connection;
    }
}
