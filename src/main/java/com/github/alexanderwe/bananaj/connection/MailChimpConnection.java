package com.github.alexanderwe.bananaj.connection;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.alexanderwe.bananaj.exceptions.MailchimpAPIException;
import com.github.alexanderwe.bananaj.model.automation.Automation;
import com.github.alexanderwe.bananaj.model.automation.AutomationStatus;
import com.github.alexanderwe.bananaj.model.automation.Automations;
import com.github.alexanderwe.bananaj.model.campaign.*;
import com.github.alexanderwe.bananaj.model.list.MailChimpList;
import com.github.alexanderwe.bananaj.model.list.MailchimpLists;
import com.github.alexanderwe.bananaj.model.list.NewMList;
import com.github.alexanderwe.bananaj.model.template.Template;
import com.github.alexanderwe.bananaj.model.template.TemplateFolder;
import com.github.alexanderwe.bananaj.model.template.TemplateFolders;
import com.github.alexanderwe.bananaj.model.template.Templates;
import com.github.alexanderwe.bananaj.utils.DateConverter;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for the com.github.alexanderwe.bananaj.connection to mailchimp servers. Used to get lists from mailchimp account.
 *
 * @author alexanderweiss
 */
public class MailChimpConnection extends Connection {

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
     * @throws Exception
     */
    public MailchimpLists getLists() throws Exception {

        HttpResponse<MailchimpLists> mailchimpListsHttpResponse = Unirest.get(this.listendpoint)
                .header("Authorization", this.apikey)
                .asObject(MailchimpLists.class);
        MailchimpLists mailLists = mailchimpListsHttpResponse.getBody();


        mailLists.getLists().forEach(list -> {
            list.setConnection(this);
        });

        return mailLists;
    }

    /**
     * Get a specific mailchimp list
     *
     * @return a Mailchimp list object
     * @throws Exception
     */
    public NewMList getList(String listID) throws Exception {

        HttpResponse<NewMList> mailchimpListsHttpResponse = Unirest.get(this.listendpoint + "/" + listID)
                .header("Authorization", this.apikey)
                .asObject(NewMList.class);
        return mailchimpListsHttpResponse.getBody();
    }


    /**
     * Create a new list in your mailchimp account
     *
     * @param listName
     */
    public void createList(String listName, String permission_reminder, boolean email_type_option, NewCampaignDefaults campaignDefaults) throws Exception {


        NewMList list = new NewMList();
        list.setName(listName);
        list.setPermission_reminder(permission_reminder);
        list.setEmail_type_option(email_type_option);
        list.setCampaign_defaults(campaignDefaults);

        HttpResponse<JsonNode> postReponse = Unirest.post(this.getListendpoint())
                .header("Authorization", this.getApikey())
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(list)
                .asJson();


        if (postReponse.getStatus() / 100 != 2) {
            throw new MailchimpAPIException(postReponse.getBody().getObject());
        }
    }

    /**
     * Delete a list from your account
     *
     * @param listID
     * @throws Exception
     */
    public void deleteList(String listID) throws Exception {

        HttpResponse<JsonNode> deleteReponse = Unirest.delete(this.getListendpoint() + "/" + listID)
                .header("Authorization", this.getApikey())
                .header("accept", "application/json")
                .asJson();

        if (deleteReponse.getStatus() / 100 != 2) {
            throw new MailchimpAPIException(deleteReponse.getBody().getObject());
        }
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
     */
    public CampaignFolders getCampaignFolders() throws Exception {
        HttpResponse<CampaignFolders> campaignFoldersHttpResponse = Unirest.get(this.getCampaignfolderendpoint())
                .header("Authorization", this.getApikey())
                .asObject(CampaignFolders.class);
        return campaignFoldersHttpResponse.getBody();
    }

    /**
     * Get a specific template folder
     *
     * @param folder_id
     * @return
     */
    public CampaignFolder getCampaignFolder(String folder_id) throws Exception {
        HttpResponse<CampaignFolder> campaignFoldersHttpResponse = Unirest.get(this.getCampaignfolderendpoint() + "/" + folder_id)
                .header("Authorization", this.getApikey())
                .asObject(CampaignFolder.class);
        return campaignFoldersHttpResponse.getBody();
    }

    /**
     * Add a template folder with a specific name
     *
     * @param name
     */
    public void addCampaignFolder(String name) throws Exception {
        CampaignFolder campaignFolder = new CampaignFolder();
        campaignFolder.setName(name);


        HttpResponse<JsonNode> postReponse = Unirest.post(this.getCampaignfolderendpoint())
                .header("Authorization", this.getApikey())
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(campaignFolder)
                .asJson();

        if (postReponse.getStatus() / 100 != 2) {
            throw new MailchimpAPIException(postReponse.getBody().getObject());
        }
    }

    /**
     * Delete a specific template folder
     *
     * @param folder_id
     */
    public void deleteCampaignFolder(String folder_id) throws Exception {
        HttpResponse<JsonNode> deleteReponse = Unirest.delete(this.getListendpoint() + "/" + folder_id)
                .header("Authorization", this.getApikey())
                .header("accept", "application/json")
                .asJson();

        if (deleteReponse.getStatus() / 100 != 2) {
            throw new MailchimpAPIException(deleteReponse.getBody().getObject());
        }
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
        JSONObject jsonCampaigns = new JSONObject(do_Get(new URL(campaignendpoint), getApikey()));
        JSONArray campaignsArray = jsonCampaigns.getJSONArray("campaigns");
        for (int i = 0; i < campaignsArray.length(); i++) {
            JSONObject campaignDetail = campaignsArray.getJSONObject(i);
            Campaign campaign = new Campaign(this, campaignDetail);
            campaigns.add(campaign);
        }
        return campaigns;
    }

    /**
     * Get a campaign from mailchimp account
     *
     * @param campaignID
     * @return a campaign object
     * @throws Exception TODO add campaignsettings
     */
    public Campaign getCampaign(String campaignID) throws Exception {
        JSONObject campaign = new JSONObject(do_Get(new URL(campaignendpoint + "/" + campaignID), getApikey()));
        return new Campaign(this, campaign);
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

        campaign = new JSONObject(do_Post(new URL(campaignendpoint), campaign.toString(), getApikey()));
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
     * @throws Exception
     */
    public void deleteCampaign(String campaign_id) throws Exception {
        HttpResponse<JsonNode> deleteReponse = Unirest.delete(this.getCampaignendpoint() + "/" + campaign_id)
                .header("Authorization", this.getApikey())
                .header("accept", "application/json")
                .asJson();

        if (deleteReponse.getStatus() / 100 != 2) {
            throw new MailchimpAPIException(deleteReponse.getBody().getObject());
        }
    }

    /**
     * Get all template folders from MailChimp
     *
     * @return
     */
    public TemplateFolders getTemplateFolders() throws Exception {
        HttpResponse<TemplateFolders> templateFoldersHttpResponse = Unirest.get(this.getTemplatefolderendpoint())
                .header("Authorization", this.getApikey())
                .asObject(TemplateFolders.class);
        return templateFoldersHttpResponse.getBody();
    }

    /**
     * Get a specific template folder
     *
     * @param folder_id
     * @return
     */
    public TemplateFolder getTemplateFolder(String folder_id) throws Exception {
        HttpResponse<TemplateFolder> templateFolderHttpResponse = Unirest.get(this.getTemplatefolderendpoint() + "/" + folder_id)
                .header("Authorization", this.getApikey())
                .asObject(TemplateFolder.class);
        return templateFolderHttpResponse.getBody();
    }

    /**
     * Add a template folder with a specific name
     *
     * @param name
     */
    public void addTemplateFolder(String name) throws Exception {
        TemplateFolder templateFolder = new TemplateFolder();
        templateFolder.setName(name);

        HttpResponse<JsonNode> postReponse = Unirest.post(this.getTemplatefolderendpoint())
                .header("Authorization", this.getApikey())
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(templateFolder)
                .asJson();

        if (postReponse.getStatus() / 100 != 2) {
            throw new MailchimpAPIException(postReponse.getBody().getObject());
        }
    }

    /**
     * Delete a specific template folder
     *
     * @param folder_id
     */
    public void deleteTemplateFolder(String folder_id) throws Exception {
        HttpResponse<JsonNode> deleteReponse = Unirest.delete(this.getTemplatefolderendpoint() + "/" + folder_id)
                .header("Authorization", this.getApikey())
                .header("accept", "application/json")
                .asJson();

        if (deleteReponse.getStatus() / 100 != 2) {
            throw new MailchimpAPIException(deleteReponse.getBody().getObject());
        }
    }

    /**
     * Get all templates from mailchimp account
     *
     * @return Arraylist containing all templates
     * @throws Exception
     */
    public Templates getTemplates() throws Exception {
        HttpResponse<Templates> templatesHttpResponse = Unirest.get(this.getTemplateendpoint())
                .header("Authorization", this.getApikey())
                .asObject(Templates.class);

        Templates templates = templatesHttpResponse.getBody();
        templates.getTemplates().forEach(template -> {
            template.setConnection(this);
        });

        return templates ;
    }

    /**
     * Get a template fom mailchimp account
     *
     * @param template_id
     * @return a template object
     * @throws Exception
     */
    public Template getTemplate(String template_id) throws Exception {
        HttpResponse<Template> templateHttpResponse = Unirest.get(this.getTemplateendpoint() + "/" + template_id)
                .header("Authorization", this.getApikey())
                .asObject(Template.class);

        Template template = templateHttpResponse.getBody();
        template.setConnection(this);
        return template;
    }

    /**
     * Add a template to your MailChimp account
     *
     * @param name
     * @param html
     * @throws Exception
     */
    public void addTemplate(String name, String html) throws Exception {
        Template template = new Template();
        template.setName(name);
        template.setHtml(html);

        HttpResponse<JsonNode> postReponse = Unirest.patch(this.getTemplateendpoint())
                .header("Authorization", this.getApikey())
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(template)
                .asJson();

        if (postReponse.getStatus() / 100 != 2) {
            throw new MailchimpAPIException(postReponse.getBody().getObject());
        }
    }

    /**
     * Add a template to a specific folder to your MailChimp Account
     *
     * @param name
     * @param folder_id
     * @param html
     * @throws Exception
     */
    public void addTemplate(String name, String folder_id, String html) throws Exception {
        Template template = new Template();
        template.setName(name);
        template.setHtml(html);
        template.setFolder_id(folder_id);


        HttpResponse<JsonNode> postReponse = Unirest.patch(this.getTemplateendpoint())
                .header("Authorization", this.getApikey())
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(template)
                .asJson();

        if (postReponse.getStatus() / 100 != 2) {
            throw new MailchimpAPIException(postReponse.getBody().getObject());
        }
    }

    /**
     * Delete a specific template
     *
     * @param template_id
     * @throws Exception
     */
    public void deleteTemplate(String template_id) throws Exception {
        HttpResponse<JsonNode> deleteReponse = Unirest.delete(this.getTemplateendpoint() + "/" + template_id)
                .header("Authorization", this.getApikey())
                .header("accept", "application/json")
                .asJson();

        if (deleteReponse.getStatus() / 100 != 2) {
            throw new MailchimpAPIException(deleteReponse.getBody().getObject());
        }
    }

    /**
     * Get all automations from mailchimp account
     *
     * @return ArrayList containing all automations
     * @throws Exception
     */
    public Automations getAutomations() throws Exception {
        HttpResponse<Automations> automationsHttpResponse = Unirest.get(this.getAutomationendpoint())
                .header("Authorization", this.getApikey())
                .asObject(Automations.class);
        return automationsHttpResponse.getBody();
    }

    /**
     * Get an specific automation
     *
     * @param id
     * @return an Automation object
     * @throws Exception
     */
    public Automation getAutomation(String id) throws Exception {
        HttpResponse<Automation> automationHttpResponse = Unirest.get(this.getAutomationendpoint())
                .header("Authorization", this.getApikey())
                .asObject(Automation.class);
        return automationHttpResponse.getBody();
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
        Account account;
        JSONObject jsonAPIROOT = new JSONObject(do_Get(new URL(apiendpoint), getApikey()));
        JSONObject contact = jsonAPIROOT.getJSONObject("contact");
        account = new Account(this, jsonAPIROOT.getString("account_id"),
                jsonAPIROOT.getString("account_name"),
                contact.getString("company"),
                contact.getString("addr1"),
                contact.getString("addr2"),
                contact.getString("city"),
                contact.getString("state"),
                contact.getString("zip"),
                contact.getString("country"),
                DateConverter.getInstance().createDateFromISO8601(jsonAPIROOT.getString("last_login")),
                jsonAPIROOT.getInt("total_subscribers"),
                jsonAPIROOT);
        this.account = account;
    }
}
