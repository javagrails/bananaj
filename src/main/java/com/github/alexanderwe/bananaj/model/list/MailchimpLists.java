package com.github.alexanderwe.bananaj.model.list;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.alexanderwe.bananaj.model.Link;

import java.util.List;


@JsonIgnoreProperties()
public class MailchimpLists {

    @JsonProperty
    List<NewMList> lists;
    @JsonProperty
    int total_items;
    @JsonProperty
    Link[] _links;

    public List<NewMList> getLists() {
        return lists;
    }

    public void setLists(List<NewMList> lists) {
        this.lists = lists;
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
