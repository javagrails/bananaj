package com.github.alexanderwe.bananaj.model.filemanager;

import com.github.alexanderwe.bananaj.connection.MailChimpConnection;
import com.github.alexanderwe.bananaj.exceptions.MailchimpAPIException;
import com.github.alexanderwe.bananaj.helper.FileInspector;
import com.github.alexanderwe.bananaj.helper.HTTPHelper;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

/**
 * Class for uploading and deleting files
 * Created by alexanderweiss on 06.02.16.
 */

public class FileManager {

    private MailChimpConnection connection;

    public FileManager(MailChimpConnection connection) {
        this.connection = connection;
    }

    /**
     * Get all file manager folders in mailchimp account account
     *
     * @return
     * @throws Exception
     */
    public FileManagerFolders getFileManagerFolders() throws MailchimpAPIException, UnirestException {

        FileManagerFolders fileManagerFolders = HTTPHelper.get(this.connection.getFilemanagerfolderendpoint(), this.connection.getApikey(), FileManagerFolders.class).getBody();
        fileManagerFolders.getFolders().forEach(folders -> {
            folders.setConnection(this.connection);
        });

        return fileManagerFolders;
    }

    /**
     * Get a specific file manager folder in mailchimp account account
     *
     * @return
     * @throws Exception
     */
    public FileManagerFolder getFileManagerFolder(int id) throws MailchimpAPIException, UnirestException {
        FileManagerFolder fileManagerFolder = HTTPHelper.get(this.connection.getFilemanagerfolderendpoint() + "/" + id, this.connection.getApikey(), FileManagerFolder.class).getBody();

        fileManagerFolder.setConnection(this.connection);
        return fileManagerFolder;
    }

    /**
     * Get all files in your account
     *
     * @return
     * @throws Exception
     */
    public FileManagerFiles getFileManagerFiles() throws MailchimpAPIException, UnirestException {
        FileManagerFiles fileManagerFiles = HTTPHelper.get(this.connection.getFilesendpoint(), this.connection.getApikey(), FileManagerFiles.class).getBody();
        fileManagerFiles.getFiles().forEach(files -> {
            files.setConnection(this.connection);
        });

        return fileManagerFiles;
    }

    /**
     * Get all files in your account
     *
     * @return
     * @throws Exception
     */
    public FileManagerFile getFileManagerFile(String id) throws MailchimpAPIException, UnirestException {
        FileManagerFile fileManagerFile = HTTPHelper.get(this.connection.getFilesendpoint() + "/" + id, this.connection.getApikey(), FileManagerFile.class).getBody();

        fileManagerFile.setConnection(this.connection);
        return fileManagerFile;
    }

    /**
     * Upload a file with folder id
     *
     * @param folder_id
     * @param filename
     * @param file
     * @throws JSONException
     * @throws MalformedURLException
     * @throws Exception
     */
    public void upload(int folder_id, String filename, File file) throws MailchimpAPIException, UnirestException {

        FileManagerFile fileManagerFile = new FileManagerFile();
        fileManagerFile.setFolder_id(folder_id);
        fileManagerFile.setName( filename + FileInspector.getInstance().getExtension(file));
        fileManagerFile.setFile_data(FileInspector.getInstance().encodeFileToBase64Binary(file));

        HTTPHelper.post(this.connection.getFilesendpoint(), fileManagerFile, this.connection.getApikey());
    }

    /**
     * Upload a file without folder id
     *
     * @param filename
     * @param file
     * @throws JSONException
     * @throws MalformedURLException
     * @throws Exception
     */
    public void upload(String filename, File file) throws MailchimpAPIException, UnirestException {
        FileManagerFile fileManagerFile = new FileManagerFile();
        fileManagerFile.setName( filename + FileInspector.getInstance().getExtension(file));
        fileManagerFile.setFile_data(FileInspector.getInstance().encodeFileToBase64Binary(file));

        HTTPHelper.post(this.connection.getFilesendpoint(), fileManagerFile, this.connection.getApikey());
    }


    public void downloadFile(String file_id, String saveDir) throws IOException, MailchimpAPIException , UnirestException {
        HTTPHelper.downloadFile(this.getFileManagerFile(file_id).getFull_size_url(), saveDir);
    }

    /**
     * Delete a file with specific fileID
     *
     * @param file_id
     * @throws Exception
     */
    public void deleteFile(String file_id) throws MailchimpAPIException, UnirestException{
        HTTPHelper.delete(connection.getFilesendpoint()+"/"+file_id, connection.getApikey());
    }

    public MailChimpConnection getConnection() {
        return connection;
    }

}
