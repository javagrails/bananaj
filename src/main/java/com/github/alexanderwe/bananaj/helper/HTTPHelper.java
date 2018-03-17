package com.github.alexanderwe.bananaj.helper;

import com.github.alexanderwe.bananaj.exceptions.MailchimpAPIException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Created by Alexander on 10.08.2016.
 */
public class HTTPHelper {

    private static final int BUFFER_SIZE = 4096;


    public static <T> HttpResponse<T> get(String url, String authorization, Class<? extends T> responseClass) throws  MailchimpAPIException, UnirestException{
        System.out.println("GET REQUEST TO: " +  url);
        HttpResponse<JsonNode> debug = Unirest.get(url)
                .header("Authorization", authorization)
                .asJson();
        System.out.println(debug.getBody().toString());


        HttpResponse<T> getResponse = Unirest.get(url)
                .header("Authorization", authorization)
                .asObject(responseClass);


        if (getResponse.getStatus() / 100 != 2) {
            throw new MailchimpAPIException();
        }
        return getResponse;
    }

    public static HttpResponse<JsonNode> post(String url, Object o, String authorization) throws  MailchimpAPIException, UnirestException{
        HttpResponse<JsonNode> postResponse = Unirest.post(url)
                .header("Authorization", authorization)
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(o)
                .asJson();

        if (postResponse.getStatus() / 100 != 2) {
            throw new MailchimpAPIException(postResponse.getBody().getObject());
        }
        return postResponse;
    }

    public static <T> HttpResponse<T> post(String url, Object o, String authorization,  Class<? extends T> responseClass) throws  MailchimpAPIException, UnirestException{
        HttpResponse<T> postResponse = Unirest.post(url)
                .header("Authorization", authorization)
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .asObject(responseClass);

        if (postResponse.getStatus() / 100 != 2) {
            throw new MailchimpAPIException(postResponse.getBody().toString());
        }
        return postResponse;
    }

    public static HttpResponse<JsonNode> patch(String url, Object o,  String authorization) throws  MailchimpAPIException, UnirestException{

        HttpResponse<JsonNode> patchResponse = Unirest.patch(url)
                .header("Authorization", authorization)
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(o)
                .asJson();
        if (patchResponse.getStatus() / 100 != 2) {
            throw new MailchimpAPIException(patchResponse.getBody().getObject());
        }
        return patchResponse;
    }


    public static HttpResponse<JsonNode> delete(String url, String authorization) throws MailchimpAPIException, UnirestException{
        HttpResponse<JsonNode> deleteResponse = Unirest.delete(url)
                .header("Authorization", authorization)
                .header("accept", "application/json")
                .asJson();

        if (deleteResponse.getStatus() / 100 != 2) {
            throw new MailchimpAPIException(deleteResponse.getBody().getObject());
        }
        return deleteResponse;
    }


    public static void downloadFile(String fileUrl, String saveDir)
            throws IOException {
        URL url = new URL(fileUrl);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        int responseCode = httpConn.getResponseCode();

        // always check HTTP response code first
        if (responseCode == HttpURLConnection.HTTP_OK) {
            String fileName = "";
            String disposition = httpConn.getHeaderField("Content-Disposition");
            String contentType = httpConn.getContentType();
            int contentLength = httpConn.getContentLength();

            if (disposition != null) {
                // extracts file name from header field
                int index = disposition.indexOf("filename=");
                if (index > 0) {
                    fileName = disposition.substring(index + 10,
                            disposition.length() - 1);
                }
            } else {
                // extracts file name from URL
                fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1,
                        fileUrl.length());
            }

            System.out.println("Content-Type = " + contentType);
            System.out.println("Content-Disposition = " + disposition);
            System.out.println("Content-Length = " + contentLength);
            System.out.println("fileName = " + fileName);

            // opens input stream from the HTTP com.github.alexanderwe.bananaj.connection
            InputStream inputStream = httpConn.getInputStream();
            String saveFilePath = saveDir + File.separator + fileName;

            // opens an output stream to save into file
            FileOutputStream outputStream = new FileOutputStream(saveFilePath);

            int bytesRead;
            byte[] buffer = new byte[BUFFER_SIZE];
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            outputStream.close();
            inputStream.close();

            System.out.println("File downloaded");
        } else {
            System.out.println("No file to download. Server replied HTTP code: " + responseCode);
        }
        httpConn.disconnect();
    }
}
