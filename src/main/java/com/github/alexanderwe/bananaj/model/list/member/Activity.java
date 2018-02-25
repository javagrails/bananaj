package com.github.alexanderwe.bananaj.model.list.member;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.alexanderwe.bananaj.model.campaign.CampaignType;

/**
 * Created by alexanderweiss on 20.01.16.
 */
public class Activity {


    @JsonProperty
    private String action;
    @JsonProperty
    private String timestamp;
    @JsonProperty
    private String url;
    @JsonProperty
    private CampaignType type;
    @JsonProperty
    private String campaign_id;
    @JsonProperty
    private String tile;
    @JsonProperty
    private String parent_campaign;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public CampaignType getType() {
        return type;
    }

    public void setType(CampaignType type) {
        this.type = type;
    }

    public String getCampaign_id() {
        return campaign_id;
    }

    public void setCampaign_id(String campaign_id) {
        this.campaign_id = campaign_id;
    }

    public String getTile() {
        return tile;
    }

    public void setTile(String tile) {
        this.tile = tile;
    }

    public String getParent_campaign() {
        return parent_campaign;
    }

    public void setParent_campaign(String parent_campaign) {
        this.parent_campaign = parent_campaign;
    }
}
