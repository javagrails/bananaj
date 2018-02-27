package com.github.alexanderwe.bananaj.helper;

import com.github.alexanderwe.bananaj.exceptions.MailchimpAPIException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Created by Alexander on 10.08.2016.
 */
public class HTTPHelper {


    public static <T> HttpResponse<T> get(String url, String authorization, Class<? extends T> responseClass) throws  MailchimpAPIException, UnirestException{
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
}
