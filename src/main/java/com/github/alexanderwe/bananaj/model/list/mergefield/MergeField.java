package com.github.alexanderwe.bananaj.model.list.mergefield;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.alexanderwe.bananaj.model.Link;

import java.util.List;

/**
 * Created by Alexander on 09.08.2016.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MergeField {


    @JsonProperty
    private String merge_id;
    @JsonProperty
    private String tag;
    @JsonProperty
    private String name;
    @JsonProperty
    private String type;
    @JsonProperty
    private boolean isRequired;
    @JsonProperty
    private String default_value;
    @JsonProperty("public")
    private boolean _public;
    @JsonProperty
    private int display_order;
    @JsonProperty
    private MergeFieldOptions options;
    @JsonProperty
    private String help_text;
    @JsonProperty
    private String list_id;
    @JsonProperty
    private List<Link> _links;

    public String getMerge_id() {
        return merge_id;
    }

    public void setMerge_id(String merge_id) {
        this.merge_id = merge_id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isRequired() {
        return isRequired;
    }

    public void setRequired(boolean required) {
        isRequired = required;
    }

    public String getDefault_value() {
        return default_value;
    }

    public void setDefault_value(String default_value) {
        this.default_value = default_value;
    }

    public boolean is_public() {
        return _public;
    }

    public void set_public(boolean _public) {
        this._public = _public;
    }

    public int getDisplay_order() {
        return display_order;
    }

    public void setDisplay_order(int display_order) {
        this.display_order = display_order;
    }

    public MergeFieldOptions getOptions() {
        return options;
    }

    public void setOptions(MergeFieldOptions options) {
        this.options = options;
    }

    public String getHelp_text() {
        return help_text;
    }

    public void setHelp_text(String help_text) {
        this.help_text = help_text;
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
}
