package com.github.alexanderwe.bananaj.model.list.mergefield;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 *  Class for representing merge field options.
 * Created by Alexander on 09.08.2016.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MergeFieldOptions {

    @JsonProperty
    private int default_country;
    @JsonProperty
    private String phone_format;
    @JsonProperty
    private String date_format;
    @JsonProperty
    private List<String> choices;
    @JsonProperty
    private int size;

    public int getDefault_country() {
        return default_country;
    }

    public void setDefault_country(int default_country) {
        this.default_country = default_country;
    }

    public String getPhone_format() {
        return phone_format;
    }

    public void setPhone_format(String phone_format) {
        this.phone_format = phone_format;
    }

    public String getDate_format() {
        return date_format;
    }

    public void setDate_format(String date_format) {
        this.date_format = date_format;
    }

    public List<String> getChoices() {
        return choices;
    }

    public void setChoices(List<String> choices) {
        this.choices = choices;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
