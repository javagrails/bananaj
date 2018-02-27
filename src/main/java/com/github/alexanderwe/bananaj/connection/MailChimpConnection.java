package com.github.alexanderwe.bananaj.connection;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.alexanderwe.bananaj.exceptions.MailchimpAPIException;
import com.github.alexanderwe.bananaj.helper.DateConverter;
import com.github.alexanderwe.bananaj.helper.HTTPHelper;
import com.github.alexanderwe.bananaj.model.Account.Account;
import com.github.alexanderwe.bananaj.model.automation.Automation;
import com.github.alexanderwe.bananaj.model.automation.Automations;
import com.github.alexanderwe.bananaj.model.campaign.*;
import com.github.alexanderwe.bananaj.model.list.MailChimpList;
import com.github.alexanderwe.bananaj.model.list.MailchimpLists;
import com.github.alexanderwe.bananaj.model.template.Template;
import com.github.alexanderwe.bananaj.model.template.TemplateFolder;
import com.github.alexanderwe.bananaj.model.template.TemplateFolders;
import com.github.alexanderwe.bananaj.model.template.Templates;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

//TODO: Idea, implement a "update" function in every object so that updates of this object locally can get cast to mailchimp

/**
 * Class for the com.github.alexanderwe.bananaj.connection to mailchimp servers. Used to get lists from mailchimp account.
 *
 * @author alexanderweiss
 */
public class MailChimpConnection {

    private String server;
    private String apikey;
    private final String apiendpoint;
    private final String listendpoint;
    private final String campaignfolderendpoint;
    private final String campaignendpoint;
    private final String templatefolderendpoint;
    private final String templateendpoint;
    private final String automationendpoint;
    private final String filemanagerfolderendpoint;
    private final String filesendpoint;
    private Account account;

    public MailChimpConnection(String apikey) {
        initUnirest();
        this.server = apikey.split("-")[1];
        this.apikey = "apikey " + apikey;
        this.apiendpoint = "https://" + server + ".api.mailchimp.com/3.0/";
        this.listendpoint = "https://" + server + ".api.mailchimp.com/3.0/lists";
        this.campaignfolderendpoint = "https://" + server + ".api.mailchimp.com/3.0/campaign-folders";
        this.campaignendpoint = "https://" + server + ".api.mailchimp.com/3.0/campaigns";
        this.templatefolderendpoint = "https://" + server + ".api.mailchimp.com/3.0/template-folders";
        this.templateendpoint = "https://" + server + ".api.mailchimp.com/3.0/templates";
        this.automationendpoint = "https://" + server + ".api.mailchimp.com/3.0/automations";
        this.filemanagerfolderendpoint = "https://" + server + ".api.mailchimp.com/3.0/file-manager/folders";
        this.filesendpoint = "https://" + server + ".api.mailchimp.com/3.0/file-manager/files";
        try {
            setAccount();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initUnirest() {
        Unirest.setObjectMapper(new ObjectMapper() {
            private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper
                    = new com.fasterxml.jackson.databind.ObjectMapper();

            public <T> T readValue(String value, Class<T> valueType) {
                try {
                    return jacksonObjectMapper.readValue(value, valueType);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            public String writeValue(Object value) {
                try {
                    return jacksonObjectMapper.writeValueAsString(value);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    /**
     * Get all lists in your account
     *
     * @return Arraylist containing all lists
     * @throws UnirestException
     */
    public MailchimpLists getLists() throws MailchimpAPIException, UnirestException {

        MailchimpLists mailLists = HTTPHelper.get(this.getListendpoint(), this.apikey, MailchimpLists.class).getBody();
        mailLists.getLists().forEach(list -> {
            list.setConnection(this);
        });

        return mailLists;
    }

    /**
     * Get a specific mailchimp list
     *
     * @return a Mailchimp list object
     * @throws UnirestException
     */
    public MailChimpList getList(String listID) throws MailchimpAPIException, UnirestException {

        MailChimpList mailChimpList = HTTPHelper.get(this.listendpoint + "/" + listID, this.apikey, MailChimpList.class).getBody();
        mailChimpList.setConnection(this);

        return mailChimpList;
    }


    /**
     * Create a new list in your mailchimp account
     *
     * @param listName
     * @throws MailchimpAPIException
     * @throws UnirestException
     */
    public void createList(String listName, String permission_reminder, boolean email_type_option, NewCampaignDefaults campaignDefaults) throws MailchimpAPIException, UnirestException {

        MailChimpList list = new MailChimpList();
        list.setName(listName);
        list.setPermission_reminder(permission_reminder);
        list.setEmail_type_option(email_type_option);
        list.setCampaign_defaults(campaignDefaults);

        HTTPHelper.post(this.getListendpoint(), list, this.getApikey());

    }

    /**
     * Delete a list from your account
     *
     * @param listID
     * @throws MailchimpAPIException
     * @throws UnirestException
     */
    public void deleteList(String listID) throws MailchimpAPIException, UnirestException {
        HTTPHelper.delete(this.getListendpoint() + "/" + listID, this.getApikey());
    }

    /**
     * Write all lists to an Excel file
     * @throws Exception
     */
	/*public void writeAllListToExcel(String filepath, boolean show_merge) throws Exception{
		WritableWorkbook workbook = Workbook.createWorkbook(new File(filepath+".xls"));
		WritableFont times16font = new WritableFont(WritableFont.TIMES, 16, WritableFont.BOLD, false);
		WritableCellFormat times16format = new WritableCellFormat (times16font);

		List<MailChimpList> mailChimpLists = getLists();
		int index  = 0;
		for(MailChimpList mailChimpList : mailChimpLists){
			WritableSheet sheet = workbook.createSheet(mailChimpList.getName(), index);

			Label memberIDLabel = new Label(0, 0, "MemberID",times16format);
			Label email_addressLabel = new Label(1,0,"Email Address",times16format);
			Label timestamp_sign_inLabel = new Label(2,0,"Sign up",times16format);
			Label ip_signinLabel = new Label(3,0,"IP Sign up", times16format);
			Label timestamp_opt_inLabel = new Label(4,0,"Opt in",times16format);
			Label ip_optLabel = new Label(5,0,"IP Opt in", times16format);
			Label statusLabel = new Label(6,0,"Status",times16format);
			Label avg_open_rateLabel = new Label(7,0,"Avg. open rate",times16format);
			Label avg_click_rateLabel = new Label(8,0,"Avg. click rate",times16format);


			sheet.addCell(memberIDLabel);
			sheet.addCell(email_addressLabel);
			sheet.addCell(timestamp_sign_inLabel);
			sheet.addCell(ip_signinLabel);
			sheet.addCell(timestamp_opt_inLabel);
			sheet.addCell(ip_optLabel);
			sheet.addCell(statusLabel);
			sheet.addCell(avg_open_rateLabel);
			sheet.addCell(avg_click_rateLabel);

			List<Member> members = mailChimpList.getMembers(0,0);
			int merge_field_count = 0;

			if (show_merge){
				int last_column = 9;

				Iterator iter = members.get(0).getMerge_fields().entrySet().iterator();
				while (iter.hasNext()) {
					Map.Entry pair = (Map.Entry)iter.next();
					sheet.addCell(new Label(last_column,0,(String)pair.getKey(),times16format));
					iter.remove(); // avoids a ConcurrentModificationException
					last_column++;
					merge_field_count++;
				}
			}


			for(int i = 0 ; i < members.size();i++)
			{
				Member member = members.get(i);
				sheet.addCell(new Label(0,i+1,member.getId()));
				sheet.addCell(new Label(1,i+1,member.getEmail_address()));
				sheet.addCell(new Label(2,i+1,member.getTimestamp_signup()));
				sheet.addCell(new Label(3,i+1,member.getIp_signup()));
				sheet.addCell(new Label(4,i+1,member.getTimestamp_opt()));
				sheet.addCell(new Label(5,i+1,member.getIp_opt()));
				sheet.addCell(new Label(6,i+1,member.getStatus().getStringRepresentation()));
				sheet.addCell(new Number(7,i+1,member.getAvg_open_rate()));
				sheet.addCell(new Number(8,i+1,member.getAvg_click_rate()));

				if (show_merge){
					//add merge fields values
					int last_index = 9;
					Iterator iter_member = member.getMerge_fields().entrySet().iterator();
					while (iter_member.hasNext()) {
						Map.Entry pair = (Map.Entry)iter_member.next();
						sheet.addCell(new Label(last_index,i+1,(String)pair.getValue()));
						iter_member.remove(); // avoids a ConcurrentModificationException
						last_index++;

					}
				}
			}

			CellView cell;

			int column_count = 9 + merge_field_count;
			for(int x=0;x<column_count;x++)
			{
				cell=sheet.getColumnView(x);
				cell.setAutosize(true);
				sheet.setColumnView(x, cell);
			}
			index++;
		}
		workbook.write();
		workbook.close();
		System.out.println("Writing to excel - done");
	}*/

    /**
     * Get all template folders from MailChimp
     *
     * @return
     * @throws MailchimpAPIException
     * @throws UnirestException
     */
    public CampaignFolders getCampaignFolders() throws MailchimpAPIException, UnirestException {
        return HTTPHelper.get(this.getCampaignfolderendpoint(), this.getApikey(), CampaignFolders.class).getBody();
    }

    /**
     * Get a specific template folder
     *
     * @param folder_id
     * @return
     * @throws MailchimpAPIException
     * @throws UnirestException
     */
    public CampaignFolder getCampaignFolder(String folder_id) throws MailchimpAPIException, UnirestException {
        return HTTPHelper.get(this.getCampaignfolderendpoint() + "/" + folder_id, this.getApikey(), CampaignFolder.class).getBody();
    }

    /**
     * Add a template folder with a specific name
     *
     * @param name
     * @throws MailchimpAPIException
     * @throws UnirestException
     */
    public void addCampaignFolder(String name) throws MailchimpAPIException, UnirestException {

        CampaignFolder campaignFolder = new CampaignFolder();
        campaignFolder.setName(name);

        HTTPHelper.post(this.getCampaignfolderendpoint(), campaignFolder, this.getApikey()).getBody();

    }

    /**
     * Delete a specific template folder
     *
     * @param folder_id
     * @throws MailchimpAPIException
     * @throws UnirestException
     */
    public void deleteCampaignFolder(String folder_id) throws MailchimpAPIException, UnirestException {
        HTTPHelper.delete(this.getCampaignfolderendpoint() + "/" + folder_id, this.getApikey());
    }

    /**
     * Get all camapaigns from mailchimp account
     *
     * @return Arraylist containing all campaigns
     * @throws Exception * TODO add campaignsettings
     */
    public List<Campaign> getCampaigns() throws Exception {

        List<Campaign> campaigns = new ArrayList<Campaign>();
        // parse response
        //JSONObject jsonCampaigns = new JSONObject(do_Get(new URL(campaignendpoint), getApikey()));
        //JSONArray campaignsArray = jsonCampaigns.getJSONArray("campaigns");
        /*for (int i = 0; i < campaignsArray.length(); i++) {
            JSONObject campaignDetail = campaignsArray.getJSONObject(i);
            Campaign campaign = new Campaign(this, campaignDetail);
            campaigns.add(campaign);
    }*/
        return null;
    }

    /**
     * Get a campaign from mailchimp account
     *
     * @param campaignID
     * @return a campaign object
     * @throws Exception TODO add campaignsettings
     */
    public Campaign getCampaign(String campaignID) throws Exception {
        //JSONObject campaign = new JSONObject(do_Get(new URL(campaignendpoint + "/" + campaignID), getApikey()));
        return null;//new Campaign(this, campaign);
    }

    /**
     * Create a new campaign in your mailchimp account
     *
     * @param type
     * @param mailChimpList
     * @param settings
     */
    public Campaign createCampaign(CampaignType type, MailChimpList mailChimpList, CampaignSettings settings) throws Exception {

        JSONObject campaign = new JSONObject();

        JSONObject recipients = new JSONObject();
        recipients.put("list_id", mailChimpList.getId());

        JSONObject jsonSettings = new JSONObject();
        put(jsonSettings, "subject_line", settings.getSubject_line());
        put(jsonSettings, "title", settings.getTitle());
        put(jsonSettings, "to_name", settings.getTo_name());
        put(jsonSettings, "from_name", settings.getFrom_name());
        put(jsonSettings, "reply_to", settings.getReply_to());
        if (settings.getTemplate_id() != 0) {
            jsonSettings.put("template_id", settings.getTemplate_id());
        }
        put(jsonSettings, "auto_footer", settings.getAuto_footer());
        put(jsonSettings, "use_conversation", settings.getUse_conversation());
        put(jsonSettings, "authenticate", settings.getAuthenticate());
        put(jsonSettings, "timewarp", settings.getTimewarp());
        put(jsonSettings, "auto_tweet", settings.getAuto_tweet());
        put(jsonSettings, "fb_comments", settings.getFb_comments());
        put(jsonSettings, "drag_and_drop", settings.getDrag_and_drop());
        put(jsonSettings, "inline_css", settings.getInline_css());
        put(jsonSettings, "folder_id", settings.getFolder_id());

        campaign.put("type", type.getStringRepresentation());
        campaign.put("recipients", recipients);
        campaign.put("settings", jsonSettings);

        //campaign = new JSONObject(do_Post(new URL(campaignendpoint), campaign.toString(), getApikey()));
        return new Campaign(this, campaign);
    }

    private JSONObject put(JSONObject settings, String key, String value) {
        if (value != null) {
            return settings.put(key, value);
        }
        return settings;
    }

    private JSONObject put(JSONObject settings, String key, Boolean value) {
        if (value != null) {
            return settings.put(key, value);
        }
        return settings;
    }

    /**
     * Delete a campaign from mailchimp account
     *
     * @param campaign_id
     * @throws MailchimpAPIException
     * @throws UnirestException
     */
    public void deleteCampaign(String campaign_id) throws MailchimpAPIException, UnirestException {
        HTTPHelper.delete(this.getCampaignendpoint() + "/" + campaign_id, this.getApikey());
    }

    /**
     * Get all template folders from MailChimp
     *
     * @return
     */
    public TemplateFolders getTemplateFolders() throws MailchimpAPIException, UnirestException {
        return HTTPHelper.get(this.getTemplatefolderendpoint(), this.getApikey(), TemplateFolders.class).getBody();
    }

    /**
     * Get a specific template folder
     *
     * @param folder_id
     * @return
     * @throws MailchimpAPIException
     * @throws UnirestException
     */
    public TemplateFolder getTemplateFolder(String folder_id) throws MailchimpAPIException, UnirestException {
        return HTTPHelper.get(this.getTemplatefolderendpoint() + "/" + folder_id, this.getApikey(), TemplateFolder.class).getBody();
    }

    /**
     * Add a template folder with a specific name
     *
     * @param name
     * @throws MailchimpAPIException
     * @throws UnirestException
     */
    public void addTemplateFolder(String name) throws MailchimpAPIException, UnirestException {
        TemplateFolder templateFolder = new TemplateFolder();
        templateFolder.setName(name);

        HTTPHelper.post(this.getTemplatefolderendpoint(), templateFolder, this.getApikey());
    }

    /**
     * Delete a specific template folder
     *
     * @param folder_id
     * @throws MailchimpAPIException
     * @throws UnirestException
     */
    public void deleteTemplateFolder(String folder_id) throws MailchimpAPIException, UnirestException {
        HTTPHelper.delete(this.getTemplatefolderendpoint() + "/" + folder_id, this.getApikey());
    }

    /**
     * Get all templates from mailchimp account
     *
     * @return Templates
     * @throws MailchimpAPIException
     * @throws UnirestException
     */
    public Templates getTemplates() throws MailchimpAPIException, UnirestException {

        Templates templates = HTTPHelper.get(this.getTemplateendpoint(), this.getApikey(), Templates.class).getBody();
        templates.getTemplates().forEach(template -> {
            template.setConnection(this);
        });

        return templates;
    }

    /**
     * Get a template fom mailchimp account
     *
     * @param template_id
     * @return a template object
     * @throws MailchimpAPIException
     * @throws UnirestException
     */
    public Template getTemplate(String template_id) throws MailchimpAPIException, UnirestException {

        Template template = HTTPHelper.get(this.getTemplateendpoint() + "/" + template_id, this.getApikey(), Template.class).getBody();
        template.setConnection(this);
        return template;
    }

    /**
     * Add a template to your MailChimp account
     *
     * @param name
     * @param html
     * @throws MailchimpAPIException
     * @throws UnirestException
     */
    public void addTemplate(String name, String html) throws MailchimpAPIException, UnirestException {

        Template template = new Template();
        template.setName(name);
        template.setHtml(html);

        HTTPHelper.post(this.getTemplateendpoint(), template, this.getApikey());
    }

    /**
     * Add a template to a specific folder to your MailChimp Account
     *
     * @param name
     * @param folder_id
     * @param html
     * @throws MailchimpAPIException
     * @throws UnirestException
     */
    public void addTemplate(String name, String folder_id, String html) throws MailchimpAPIException, UnirestException {

        Template template = new Template();
        template.setName(name);
        template.setHtml(html);
        template.setFolder_id(folder_id);

        HTTPHelper.post(this.getTemplateendpoint(), template, this.getApikey());
    }

    /**
     * Delete a specific template
     *
     * @param template_id
     * @throws MailchimpAPIException
     * @throws UnirestException
     */
    public void deleteTemplate(String template_id) throws MailchimpAPIException, UnirestException {
        HTTPHelper.delete(this.getTemplateendpoint() + "/" + template_id, this.getApikey());
    }

    /**
     * Get all automations from mailchimp account
     *
     * @return Automations
     * @throws MailchimpAPIException
     * @throws UnirestException
     */
    public Automations getAutomations() throws MailchimpAPIException, UnirestException {

        Automations automations = HTTPHelper.get(this.getAutomationendpoint(), this.getApikey(), Automations.class).getBody();
        automations.getAutomations().forEach(automation -> {
            automation.setConnection(this);
        });

        return automations;
    }

    /**
     * Get an specific automation
     *
     * @param automation_id
     * @return an Automation object
     * @throws MailchimpAPIException
     * @throws UnirestException
     */
    public Automation getAutomation(String automation_id) throws MailchimpAPIException, UnirestException {
        Automation automation = HTTPHelper.get(this.getAutomationendpoint() + "/" + automation_id, this.getApikey(), Automation.class).getBody();
        automation.setConnection(this);

        return automation;
    }

    /**
     * @return the server
     */
    public String getServer() {
        return this.server;
    }

    /**
     * @return the apikey
     */
    public String getApikey() {
        return this.apikey;
    }

    /**
     * @return the apiendpoint
     */
    public String getApiendpoint() {
        return this.apiendpoint;
    }

    /**
     * @return the lISTENDPOINT
     */
    public String getListendpoint() {
        return this.listendpoint;
    }

    /**
     * @return the campaignendpoint
     */
    public String getCampaignendpoint() {
        return this.campaignendpoint;
    }

    /**
     * @return the templateendpoint
     */
    public String getTemplateendpoint() {
        return this.templateendpoint;
    }

    /**
     * @return the automationendpoint
     */
    public String getAutomationendpoint() {
        return this.automationendpoint;
    }

    /**
     * @return the filemanagerfolderendpoint
     */
    public String getFilemanagerfolderendpoint() {
        return this.filemanagerfolderendpoint;
    }


    public String getFilesendpoint() {
        return filesendpoint;
    }

    public String getCampaignfolderendpoint() {
        return this.campaignfolderendpoint;
    }

    public String getTemplatefolderendpoint() {
        return this.templatefolderendpoint;
    }

    /**
     * @return the account
     */
    public Account getAccount() {
        return this.account;
    }

    /**
     * Set the account of this com.github.alexanderwe.bananaj.connection.
     */
    private void setAccount() throws Exception {
        Account account = HTTPHelper.get(this.apiendpoint, this.apikey, Account.class).getBody();
        account.setApiKey(this.apikey);
        this.account = account;
    }
}
