package com.github.alexanderwe.bananaj.model.list.mergefield;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.alexanderwe.bananaj.model.Link;

import java.util.List;

public class MergeFields {

    @JsonProperty
    List<MergeField> merge_fields;
    @JsonProperty
    String list_id;
    @JsonProperty
    int total_items;
    @JsonProperty
    Link[] _links;

    public List<MergeField> getMergeFields() {
        return merge_fields;
    }

    public void setMergeFields(List<MergeField> merge_fields) {
        this.merge_fields = merge_fields;
    }

    public String getList_id() {
        return list_id;
    }

    public void setList_id(String list_id) {
        this.list_id = list_id;
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
