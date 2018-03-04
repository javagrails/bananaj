package com.github.alexanderwe.bananaj.model.filemanager;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.alexanderwe.bananaj.model.Link;

import java.util.List;

public class FileManagerFiles {

    @JsonProperty
    List<FileManagerFile> files;
    @JsonProperty
    int total_items;
    @JsonProperty
    int total_file_size;
    @JsonProperty
    Link[] _links;

    public List<FileManagerFile> getFiles() {
        return files;
    }

    public void setFiles(List<FileManagerFile> lists) {
        this.files = lists;
    }

    public int getTotal_items() {
        return total_items;
    }

    public void setTotal_items(int total_items) {
        this.total_items = total_items;
    }

    public int getTotal_file_size() {
        return total_file_size;
    }

    public void setTotal_file_size(int total_file_size) {
        this.total_file_size = total_file_size;
    }

    public Link[] get_links() {
        return _links;
    }

    public void set_links(Link[] _links) {
        this._links = _links;
    }
}
