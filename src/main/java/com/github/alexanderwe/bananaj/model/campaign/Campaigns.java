package com.github.alexanderwe.bananaj.model.campaign;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.alexanderwe.bananaj.model.Link;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Campaigns {

    @JsonProperty
    List<Campaign> campaigns;
    @JsonProperty
    int total_items;
    @JsonProperty
    Link[] _links;

    public List<Campaign> getCampaigns() {
        return campaigns;
    }

    public void setCampaigns(List<Campaign> campaigns) {
        this.campaigns = campaigns;
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
