package com.github.alexanderwe.bananaj.model.filemanager;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.alexanderwe.bananaj.connection.MailChimpConnection;
import com.github.alexanderwe.bananaj.exceptions.MailchimpAPIException;
import com.github.alexanderwe.bananaj.helper.HTTPHelper;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Class for representing a file manager folder.
 * Created by alexanderweiss on 22.01.16.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FileManagerFolder {

    @JsonProperty
    private int id;
    @JsonProperty
    private String name;
    @JsonProperty
    private int file_count;
    @JsonProperty
    private LocalDateTime created_at;
    @JsonProperty
    private String created_by;

    private ArrayList<FileManagerFile> files;
    private MailChimpConnection connection;


    public void changeName(String name) throws MailchimpAPIException, UnirestException {
        FileManagerFolder fileManagerFolder = new FileManagerFolder();
        fileManagerFolder.setName(name);

        HTTPHelper.patch(this.connection.getFilemanagerfolderendpoint() + "/" + this.getId(), fileManagerFolder, this.connection.getApikey());
        this.name = name;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFile_count() {
        return file_count;
    }

    public void setFile_count(int file_count) {
        this.file_count = file_count;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public ArrayList<FileManagerFile> getFiles() {
        return files;
    }

    public void setFiles(ArrayList<FileManagerFile> files) {
        this.files = files;
    }

    public MailChimpConnection getConnection() {
        return connection;
    }

    public void setConnection(MailChimpConnection connection) {
        this.connection = connection;
    }
}
