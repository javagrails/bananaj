package com.github.alexanderwe.bananaj.model.template;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.alexanderwe.bananaj.model.Link;

import java.util.List;

public class TemplateFolders {

    @JsonProperty
    List<TemplateFolder> templateFolders;
    @JsonProperty
    int total_items;
    @JsonProperty
    Link[] _links;

    public List<TemplateFolder> getTemplateFolders() {
        return templateFolders;
    }

    public void setLists(List<TemplateFolder> templateFolders) {
        this.templateFolders = templateFolders;
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
