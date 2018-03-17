package com.github.alexanderwe.bananaj.model.list.segment;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by alexanderweiss on 27.12.16.
 */
public enum Operator {

    @JsonProperty("open") OPEN("open"),
    @JsonProperty("click") CLICK("click"),
    @JsonProperty("sent") SENT("sent"),
    @JsonProperty("noopen") NOOPEN("noopen"),
    @JsonProperty("noclick") NOCLICK("noclick"),
    @JsonProperty("nosent") NOSENT("nosent"),
    @JsonProperty("started") STARTED("started"),
    @JsonProperty("completed") COMPLETED("completed"),
    @JsonProperty("not_started") NOT_STARTED("not_started"),
    @JsonProperty("not_completed") NOT_COMPLETED("not_completed"),
    @JsonProperty("member") MEMBER("member"),
    @JsonProperty("notmember") NOTMEMBER("notmember"),
    @JsonProperty("greater") GREATER("greater"),
    @JsonProperty("less") LESS("less"),
    @JsonProperty("is") IS("is"),
    @JsonProperty("not") NOT("not"),
    @JsonProperty("blank") BLANK("blank"),
    @JsonProperty("blank_not") BLANK_NOT("blank_not"),
    @JsonProperty("client_is") CLIENT_IS("client_is"),
    @JsonProperty("client_not") CLIENT_NOT("client_not"),
    @JsonProperty("source_is") SOURCE_IS("source_is"),
    @JsonProperty("source_not") SOURCE_NOT("source_not"),
    @JsonProperty("interestcontains") INTERESTCONTAINS("interestcontains"),
    @JsonProperty("interestcontainsall") INTERESTCONTAINSALL("interestcontainsall"),
    @JsonProperty("interestnotcontains") INTERESTNOTCONTAINS("interestnotcontains"),
    @JsonProperty("contains") CONTAINS("contains"),
    @JsonProperty("notcontain") NOTCONTAIN("notcontain"),
    @JsonProperty("starts") STARTS("starts"),
    @JsonProperty("ends") ENDS("ends"),
    @JsonProperty("goal_not") GOAL_NOT("goal_not"),
    @JsonProperty("goal_notcontain") GOAL_NOTCONTAIN("goal_notcontain"),
    @JsonProperty("fuzzy_is") FUZZY_IS("fuzzy_is"),
    @JsonProperty("fuzzy_not") FUZZY_NOT("fuzzy_not"),
    @JsonProperty("static_is") STATIC_IS("static_is"),
    @JsonProperty("static_not") STATIC_NOT("static_not"),
    @JsonProperty("ipgeocountry") IPGEOCOUNTRY("ipgeocountry"),
    @JsonProperty("ipgeonotcountry") IPGEONOTCOUNTRY("ipgeonotcountry"),
    @JsonProperty("ipgeostate") IPGEOSTATE("ipgeostate"),
    @JsonProperty("ipgeonotstate") IPGEONOTSTATE("ipgeonotstate"),
    @JsonProperty("ipgeoin") IPGEOIN("ipgeoin"),
    @JsonProperty("ipgeonotin") IPGEONOTIN("ipgeonotin"),
    @JsonProperty("ipgeoinzip") IPGEOINZIP("ipgeoinzip"),
    @JsonProperty("ipgeounknown") IPGEOUNKNOWN("ipgeounknown"),
    @JsonProperty("ipgeoiszip") IPGEOISZIP("ipgeoiszip"),
    @JsonProperty("ipgeonotzip") IPGEONOTZIP("ipgeonotzip"),
    @JsonProperty("follow") FOLLOW("follow"),
    @JsonProperty("notfollow") NOTFOLLOW("notfollow"),
    @JsonProperty("geoin") GEOIN("geoin");

    private final String value;
    private final static Map<String, Operator> CONSTANTS = new HashMap<String, Operator>();

    static {
        for (Operator c: values()) {
            CONSTANTS.put(c.value, c);
        }
    }

    private Operator(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    public String value() {
        return value;
    }

    public static Operator fromValue(String value) {
        Operator constant = CONSTANTS.get(value);
        if (constant == null) {
            throw new IllegalArgumentException(value);
        } else {
            return constant;
        }
    }
}

