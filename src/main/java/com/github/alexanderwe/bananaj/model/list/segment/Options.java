package com.github.alexanderwe.bananaj.model.list.segment;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexanderweiss on 04.02.16.
 */
public class Options {

    @JsonProperty
    private MatchType match;
    @JsonProperty
    private List<Condition> conditions;

    public void addCondition(Condition condition){
        this.conditions.add(condition);
    }

    public MatchType getMatchType() {
        return match;
    }

    public void setMatchType(MatchType match) {
        this.match = match;
    }

    public List<Condition> getConditions() {
        return conditions;
    }

    public void setConditions(ArrayList<Condition> conditions) {
        this.conditions = conditions;
    }


    @Override
    public String toString(){
        return "Match type: " + this.getMatchType().getStringRepresentation() +  System.lineSeparator() +
                "Conditions: " + this.getConditions() + System.lineSeparator();
    }


}
