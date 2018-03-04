package com.github.alexanderwe.bananaj.model.filemanager;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.alexanderwe.bananaj.model.Link;

import java.util.List;

public class FileManagerFolders {

    @JsonProperty
    List<FileManagerFolder> folders;
    @JsonProperty
    int total_items;
    @JsonProperty
    Link[] _links;

    public List<FileManagerFolder> getFolders() {
        return folders;
    }

    public void setFolders(List<FileManagerFolder> folders) {
        this.folders = folders;
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
