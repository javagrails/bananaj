package com.github.alexanderwe.bananaj.model.list;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.alexanderwe.bananaj.connection.MailChimpConnection;
import com.github.alexanderwe.bananaj.exceptions.EmailException;
import com.github.alexanderwe.bananaj.exceptions.FileFormatException;
import com.github.alexanderwe.bananaj.exceptions.MailchimpAPIException;
import com.github.alexanderwe.bananaj.helper.EmailValidator;
import com.github.alexanderwe.bananaj.helper.FileInspector;
import com.github.alexanderwe.bananaj.helper.HTTPHelper;
import com.github.alexanderwe.bananaj.model.Link;
import com.github.alexanderwe.bananaj.model.campaign.NewCampaignDefaults;
import com.github.alexanderwe.bananaj.model.list.member.Member;
import com.github.alexanderwe.bananaj.model.list.member.MemberStatus;
import com.github.alexanderwe.bananaj.model.list.member.Members;
import com.github.alexanderwe.bananaj.model.list.mergefield.MergeField;
import com.github.alexanderwe.bananaj.model.list.mergefield.MergeFields;
import com.github.alexanderwe.bananaj.model.list.segment.Options;
import com.github.alexanderwe.bananaj.model.list.segment.Segment;
import com.github.alexanderwe.bananaj.model.list.segment.Segments;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import jxl.*;
import jxl.read.biff.BiffException;
import jxl.write.*;
import jxl.write.Number;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class MailChimpList {


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
    private Object modules; //TODO: Add
    @JsonProperty
    private Stats stats;
    @JsonProperty
    List<Link> _links;

    /**
     * Get all members in this list
     *
     * @param count  x first members
     * @param offset skip x first members
     * @return
     * @throws Exception
     */
    public Members getMembers(int count, int offset) throws  MailchimpAPIException, UnirestException {

        String url;
        if (count != 0) {
            url = this.connection.getListendpoint() + "/" + this.getId() + "/members?count=" + count + "&offset=" + offset;
        } else {
            url = this.connection.getListendpoint() + "/" + this.getId() + "/members?count=" + this.getStats().getMember_count() + "&offset=" + offset;
        }

        Members members = HTTPHelper.get(url, this.connection.getApikey(), Members.class).getBody();


        members.getMembers().forEach(member -> {
            member.setConnection(this.getConnection());
        });

        return members;
    }

    /**
     * Get a single member from list
     *
     * @param member_id
     * @return
     * @throws Exception
     */
    public Member getMember(String member_id) throws MailchimpAPIException, UnirestException{

        Member member = HTTPHelper.get(this.connection.getListendpoint() + "/" + this.getId() + "/members/" + member_id, this.connection.getApikey(), Member.class).getBody();
        member.setConnection(this.getConnection());
        return member;
    }

    /**
     * Add a member with the minimum of information
     *
     * @param status
     * @param emailAddress
     */
    public void addMember(MemberStatus status, String emailAddress) throws MailchimpAPIException, UnirestException {

        Member member = new Member();
        member.setEmail_address(emailAddress);
        member.setStatus(status);

       HTTPHelper.post(this.connection.getListendpoint() + "/" + this.getId() + "/members/",member, this.connection.getApikey()).getBody();

        int updateMemberCount = this.stats.getMember_count();
        this.stats.setMember_count(++updateMemberCount);
    }

    /**
     * Add a member with first and last name
     *
     * @param status
     * @param emailAddress
     * @param merge_fields_values
     * @throws Exception TODO:
     */
    public void addMember(MemberStatus status, String emailAddress, HashMap<String, Object> merge_fields_values) throws Exception {
        URL url = new URL(connection.getListendpoint() + "/" + this.getId() + "/members");

        JSONObject member = new JSONObject();
        JSONObject merge_fields = new JSONObject();

        Iterator it = merge_fields_values.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            it.remove(); // avoids a ConcurrentModificationException
            merge_fields.put(pair.getKey().toString(), pair.getValue());
        }

        member.put("status", status.getStringRepresentation());
        member.put("email_address", emailAddress);
        member.put("merge_fields", merge_fields);
       // getConnection().do_Post(new URL(connection.getListendpoint() + "/" + this.getId() + "/members"), member.toString(), connection.getApikey());
        int updateMemberCount = this.stats.getMember_count();
        this.stats.setMember_count(++updateMemberCount);
    }

    //TODO: implement
    public void importMembersFromFile(File file) throws FileFormatException, IOException {
        //TODO fully implement read from xls
        String extension = FileInspector.getInstance().getExtension(file);

        if (extension.equals(".xls") || extension.equals(".xlsx")) {
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
     *
     * @param member_id
     * @throws Exception
     */
    public void deleteMember(String member_id) throws MailchimpAPIException, UnirestException {

        HTTPHelper.delete(this.connection.getListendpoint() + "/" + this.getId() + "/members/" + member_id, this.connection.getApikey());
        int updateMemberCount = this.stats.getMember_count();
        this.stats.setMember_count(--updateMemberCount);
    }

    /**
     * Get the growth history of this list
     *
     * @return a growth history
     * @throws Exception
     */
    public GrowthHistory getGrowthHistory() throws MailchimpAPIException, UnirestException {
        return HTTPHelper.get(this.connection.getListendpoint() + "/" + this.getId() + "/growth-history", this.connection.getApikey(),  GrowthHistory.class).getBody();
    }

    /**
     * Get all segments of this list
     *
     * @return
     * @throws Exception
     */
    public Segments getSegments() throws MailchimpAPIException,UnirestException {
        return HTTPHelper.get(this.connection.getListendpoint() + "/" + this.getId() + "/segments", this.getConnection().getApikey(), Segments.class).getBody();
    }

    /**
     * Get a specific segment of this list
     *
     * @param segment_id
     * @return
     * @throws Exception
     */
    public Segment getSegment(String segment_id) throws  MailchimpAPIException, UnirestException {
        return HTTPHelper.get(this.connection.getListendpoint() + "/" + this.getId() + "/segments/" + segment_id, this.getConnection().getApikey(), Segment.class).getBody();
    }

    /**
     * Add a segment to the list
     *
     * @param name
     * @param option
     * @throws Exception TODO: Add options
     */
    public void addSegment(String name, Options option) throws MailchimpAPIException, UnirestException {

        Segment segment = new Segment();
        segment.setName(name);

        HTTPHelper.post(this.connection.getListendpoint() + "/" + this.getId() + "/segments", segment, this.getConnection().getApikey());
    }

    /**
     * Add a static segment with a name and predefined emails to this list.
     * Every E-Mail address which is not present on the list itself will be ignored and not added to the static segment.
     *
     * @param name
     * @param emails
     * @throws Exception TODO: Add options
     */
    public void addStaticSegment(String name, String[] emails, Options options) throws EmailException, MailchimpAPIException, UnirestException {

        Segment segment = new Segment();
        segment.setName(name);
        for (String email : emails) {
            if (!EmailValidator.getInstance().validate(email)) {
                throw new EmailException(email);
            }
        }
        segment.setStatic_segment(emails);

        HTTPHelper.post(this.connection.getListendpoint() + "/" + this.getId() + "/segments", segment, this.getConnection().getApikey());
    }


    public void deleteSegement(String segment_id) throws MailchimpAPIException, UnirestException {
        HTTPHelper.delete(this.connection.getListendpoint() + "/" + this.getId() + "/segments/" + segment_id, this.getConnection().getApikey());
    }


    /**
     * Get a list of all merge fields of this list
     *
     * @return
     * @throws Exception
     */
    public MergeFields getMergeFields() throws MailchimpAPIException, UnirestException {
        return  HTTPHelper.get(this.connection.getListendpoint() + "/" + this.getId() + "/merge-fields", this.getConnection().getApikey(), MergeFields.class).getBody();
    }

    /**
     * Get a specific merge field of this list
     *
     * @param mergeField_id
     * @return
     */
    public MergeField getMergeField(String mergeField_id) throws MailchimpAPIException, UnirestException {
        return  HTTPHelper.get(this.connection.getListendpoint() + "/" + this.getId() + "/merge-fields/"+ mergeField_id, this.getConnection().getApikey(), MergeField.class).getBody();

    }

    //TODO: ADD
    public void addMergeField(MergeField mergeFieldtoAdd) {

    }


    public void deleteMergeField(String mergeField_id) throws MailchimpAPIException, UnirestException {
        HTTPHelper.delete(this.connection.getListendpoint() + "/" + this.getId() + "/merge-fields/" + mergeField_id, this.getConnection().getApikey());
    }

    /**
     * Writes the data of this list to an excel file in current directory. Define whether to show merge fields or not
     *
     * @param show_merge
     * @throws Exception
     * TODO: add .xlsx and rework it a little bit
     */
    public void writeToExcel(String filepath, boolean show_merge) throws Exception {
        List<Member> members = this.getMembers(0, 0).getMembers();
        int merge_field_count = 0;
        WritableWorkbook workbook;


        if (filepath.contains(".xls")) {
            workbook = Workbook.createWorkbook(new File(filepath));
        } else {
            workbook = Workbook.createWorkbook(new File(filepath + ".xls"));
        }

        WritableSheet sheet = workbook.createSheet(this.getName(), 0);


        WritableFont times16font = new WritableFont(WritableFont.TIMES, 16, WritableFont.BOLD, false);
        WritableCellFormat times16format = new WritableCellFormat(times16font);

        Label memberIDLabel = new Label(0, 0, "MemberID", times16format);
        Label email_addressLabel = new Label(1, 0, "Email Address", times16format);
        Label timestamp_sign_inLabel = new Label(2, 0, "Sign up", times16format);
        Label ip_signinLabel = new Label(3, 0, "IP Sign up", times16format);
        Label timestamp_opt_inLabel = new Label(4, 0, "Opt in", times16format);
        Label ip_optLabel = new Label(5, 0, "IP Opt in", times16format);
        Label statusLabel = new Label(6, 0, "Status", times16format);
        Label avg_open_rateLabel = new Label(7, 0, "Avg. open rate", times16format);
        Label avg_click_rateLabel = new Label(8, 0, "Avg. click rate", times16format);


        sheet.addCell(memberIDLabel);
        sheet.addCell(email_addressLabel);
        sheet.addCell(timestamp_sign_inLabel);
        sheet.addCell(ip_signinLabel);
        sheet.addCell(timestamp_opt_inLabel);
        sheet.addCell(ip_optLabel);
        sheet.addCell(statusLabel);
        sheet.addCell(avg_open_rateLabel);
        sheet.addCell(avg_click_rateLabel);

        if (show_merge) {
            int last_column = 9;

            Iterator iter = members.get(0).getMerge_fields().entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry pair = (Map.Entry) iter.next();
                sheet.addCell(new Label(last_column, 0, (String) pair.getKey(), times16format));
                iter.remove(); // avoids a ConcurrentModificationException
                last_column++;
                merge_field_count++;
            }
        }


        for (int i = 0; i < members.size(); i++) {
            Member member = members.get(i);
            sheet.addCell(new Label(0, i + 1, member.getId()));
            sheet.addCell(new Label(1, i + 1, member.getEmail_address()));
            sheet.addCell(new Label(2, i + 1, member.getTimestamp_signup()));
            sheet.addCell(new Label(3, i + 1, member.getIp_signup()));
            sheet.addCell(new Label(4, i + 1, member.getTimestamp_opt()));
            sheet.addCell(new Label(5, i + 1, member.getIp_opt()));
            sheet.addCell(new Label(6, i + 1, member.getStatus().getStringRepresentation()));
            sheet.addCell(new Number(7, i + 1, member.getStats().getAvg_open_rate()));
            sheet.addCell(new Number(8, i + 1, member.getStats().getAvg_click_rate()));

            if (show_merge) {
                //add merge fields values
                int last_index = 9;
                Iterator iter_member = member.getMerge_fields().entrySet().iterator();
                while (iter_member.hasNext()) {
                    Map.Entry pair = (Map.Entry) iter_member.next();
                    sheet.addCell(new Label(last_index, i + 1, (String) pair.getValue()));
                    iter_member.remove(); // avoids a ConcurrentModificationException
                    last_index++;

                }
            }
        }

        CellView cell;

        int column_count = 9 + merge_field_count;
        for (int x = 0; x < column_count; x++) {
            cell = sheet.getColumnView(x);
            cell.setAutosize(true);
            sheet.setColumnView(x, cell);
        }


        workbook.write();
        workbook.close();

        System.out.println("Writing to excel - done");
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
