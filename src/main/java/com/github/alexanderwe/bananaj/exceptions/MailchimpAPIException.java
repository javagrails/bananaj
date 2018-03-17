package com.github.alexanderwe.bananaj.exceptions;

import org.json.JSONObject;

public class MailchimpAPIException extends Exception{


    private JSONObject jsonRepresentation;

    public MailchimpAPIException() {
    }

    public MailchimpAPIException(JSONObject jsonObject) {
        super(jsonObject.toString());
        this.jsonRepresentation = jsonObject;
    }

    public MailchimpAPIException(String message) {
        super(message);
    }
}
