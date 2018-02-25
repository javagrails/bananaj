package com.github.alexanderwe.bananaj.model.list.member;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.alexanderwe.bananaj.model.Link;

import java.util.List;

public class Activities {


    @JsonProperty
    private List<Activity> activity;
    @JsonProperty
    private String email_id;
    @JsonProperty
    private String list_id;
    @JsonProperty
    private int total_items;
    @JsonProperty
    private Link[] _links;

    public List<Activity> getActivity() {
        return activity;
    }

    public void setTemplates(List<Activity> memberActivities) {
        this.activity = memberActivities;
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

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public String getList_id() {
        return list_id;
    }

    public void setList_id(String list_id) {
        this.list_id = list_id;
    }
}
