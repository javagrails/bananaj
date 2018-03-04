package com.github.alexanderwe.bananaj.model.list.segment;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum ConditionType {
    @JsonProperty("Aim") AIM("Aim"),
    @JsonProperty("Automation") AUTOMATION("Automation"),
    @JsonProperty("CampaignPoll") CAMPAIGN_POLL("CampaignPoll"),
    @JsonProperty("Conversation") CONVERSATION("Conversation"),
    @JsonProperty("Date") DATE("Date"),
    @JsonProperty("EmailClient") EMAIL_CLIENT("EmailClient"),
    @JsonProperty("Language") LANGUAGE("Language"),
    @JsonProperty("Mandrill")  MANDRILL("Mandrill"),
    @JsonProperty("MemberRating") MEMBER_RATING("MemberRating"),
    @JsonProperty("SignupSource") SIGNUP_SOURCE("SignupSource"),
    @JsonProperty("SurveyMonkey") SURVEY_MONKEY("SurveyMonkey"),
    @JsonProperty("VIP") VIP("VIP"),
    @JsonProperty("Interests") INTERESTS("Interests"),
    @JsonProperty("EcommCategory") ECOMM_CATEGORY("EcommCategory"),
    @JsonProperty("EcommNumber") ECOMM_NUMBER("EcommNumber"),
    @JsonProperty("EcommPurchased") ECOMM_PURCHASED("EcommPurchased"),
    @JsonProperty("EcommSpent") ECOMM_SPENT("EcommSpent"),
    @JsonProperty("EcommStore") ECOMM_STORE("EcommStore"),
    @JsonProperty("GoalActivity") GOAL_ACTIVITY("GoalActivity"),
    @JsonProperty("GoalTimestamp") GOAL_TIMESTAMP("GoalTimestamp"),
    @JsonProperty("FuzzySegment") FUZZY_SEGMENT("FuzzySegment"),
    @JsonProperty("StaticSegment") STATIC_SEGMENT("StaticSegment"),
    @JsonProperty("IPGeoCountryState")  IP_GEO_COUNTRY_STATE("IPGeoCountryState"),
    @JsonProperty("IPGeoIn") IP_GEO_IN("IPGeoIn"),
    @JsonProperty("IPGeoInZip")  IP_GEO_IN_ZIP("IPGeoInZip"),
    @JsonProperty("IPGeoUnknown") IP_GEO_UNKNOWN("IPGeoUnknown"),
    @JsonProperty("IPGeoZip")  IP_GEO_ZIP("IPGeoZip"),
    @JsonProperty("SocialAge")  SOCIAL_AGE("SocialAge"),
    @JsonProperty("SocialGender")  SOCIAL_GENDER("SocialGender"),
    @JsonProperty("SocialInfluence") SOCIAL_INFLUENCE("SocialInfluence"),
    @JsonProperty("SocialNetworkMember") SOCIAL_NETWORK_MEMBER("SocialNetworkMember"),
    @JsonProperty("SocialNetworkFollow")  SOCIAL_NETWORK_FOLLOW("SocialNetworkFollow"),
    @JsonProperty("AddressMerge") ADDRESS_MERGE("AddressMerge"),
    @JsonProperty("ZipMerge")  ZIP_MERGE("ZipMerge"),
    @JsonProperty("BirthdayMerge")  BIRTHDAY_MERGE("BirthdayMerge"),
    @JsonProperty("DateMerge") DATE_MERGE("DateMerge"),
    @JsonProperty("TextMerge") TEXT_MERGE("TextMerge"),
    @JsonProperty("SelectMerge") SELECT_MERGE("SelectMerge"),
    @JsonProperty("EmailAddress") EMAIL_ADDRESS("EmailAddress");

    private final String value;
    private ConditionType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    public String value() {
        return value;
    }
}

