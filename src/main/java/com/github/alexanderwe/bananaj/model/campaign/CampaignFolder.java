package com.github.alexanderwe.bananaj.model.campaign;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.alexanderwe.bananaj.model.Link;
import com.github.alexanderwe.bananaj.model.MailchimpObject;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by alexanderweiss on 10.08.2016.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CampaignFolder extends MailchimpObject{


    @JsonProperty
    private String id;
    @JsonProperty
    private String name;
    @JsonProperty
    private int count;
    @JsonProperty
    private List<Link> _links;


    @Override
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Link> get_links() {
        return _links;
    }

    public void set_links(List<Link> _links) {
        this._links = _links;
    }
}