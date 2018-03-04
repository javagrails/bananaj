package com.github.alexanderwe.bananaj.model.filemanager;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.alexanderwe.bananaj.connection.MailChimpConnection;
import com.github.alexanderwe.bananaj.exceptions.MailchimpAPIException;
import com.github.alexanderwe.bananaj.helper.HTTPHelper;
import com.github.alexanderwe.bananaj.model.Link;
import com.github.alexanderwe.bananaj.model.MailchimpObject;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;
import com.github.alexanderwe.bananaj.helper.FileInspector;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

/**
 * Class for representing one specific file manager file.
 * Created by alexanderweiss on 22.01.16.
 * TODO change methods are not working
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FileManagerFile {

    @JsonProperty
    private String id;
    @JsonProperty
    private int folder_id;
    @JsonProperty
    private String type;
    @JsonProperty
    private String name;
    @JsonProperty
    private String full_size_url;
    @JsonProperty
    private String thumbnail_url;
    @JsonProperty
    private int size;
    @JsonProperty
    private LocalDateTime created_at;
    @JsonProperty
    private String created_by;
    @JsonProperty
    private int width;
    @JsonProperty
    private int height;
    @JsonProperty
    private List<Link> _links;
    @JsonProperty
    private String file_data;

    private MailChimpConnection connection;


    /**
     * Change the name of the file
     * @param name
     * @
     */
    private void changeName(String name) throws MailchimpAPIException, UnirestException{
        FileManagerFile fileManagerFile = new FileManagerFile();
        fileManagerFile.setName(name);
        fileManagerFile.setFolder_id(this.folder_id);

        HTTPHelper.patch(this.connection.getFilesendpoint()+"/"+this.getId(), fileManagerFile, this.connection.getApikey());
        this.name = name;
    }

    /**
     * Change the folder of this file
     * @param folder_id
     */
    private void changeFolder(String folder_id) throws MailchimpAPIException, UnirestException{
        FileManagerFile fileManagerFile = new FileManagerFile();
        fileManagerFile.setName(this.getName());
        fileManagerFile.setFolder_id(this.folder_id);

        HTTPHelper.patch(this.connection.getFilesendpoint()+"/"+this.getId(), fileManagerFile, this.connection.getApikey());
        this.name = name;
    }

    //http://www.codejava.net/java-se/networking/use-httpurlconnection-to-download-file-from-an-http-url
    public void downloadFile(String saveDir) throws IOException {
        HTTPHelper.downloadFile(this.getFull_size_url(), saveDir);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getFolder_id() {
        return folder_id;
    }

    public void setFolder_id(int folder_id) {
        this.folder_id = folder_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFull_size_url() {
        return full_size_url;
    }

    public void setFull_size_url(String full_size_url) {
        this.full_size_url = full_size_url;
    }

    public String getThumbnail_url() {
        return thumbnail_url;
    }

    public void setThumbnail_url(String thumbnail_url) {
        this.thumbnail_url = thumbnail_url;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        OffsetDateTime offsetDateTime = OffsetDateTime.parse(created_at);
        this.created_at = offsetDateTime.toLocalDateTime();
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public List<Link> get_links() {
        return _links;
    }

    public void set_links(List<Link> _links) {
        this._links = _links;
    }

    public String getFile_data() {
        return file_data;
    }

    public void setFile_data(String file_data) {
        this.file_data = file_data;
    }

    public MailChimpConnection getConnection() {
        return connection;
    }

    public void setConnection(MailChimpConnection connection) {
        this.connection = connection;
    }

}
