package com.github.alexanderwe.bananaj.model.list.segment;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by alexanderweiss on 27.12.16.
 */
public class Condition {

    @JsonProperty
    private ConditionType condition_type;
    @JsonProperty
    private String field;
    @JsonProperty
    private Operator op;
    protected Map<String,Object> value = new HashMap<String,Object>();

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Operator getOp() {
        return op;
    }

    public void setOp(Operator operator) {
        this.op = operator;
    }

    public ConditionType getCondition_type() {
        return condition_type;
    }

    public void setCondition_type(ConditionType condition_type) {
        this.condition_type = condition_type;
    }

    @JsonAnyGetter
    public Map<String,Object> getValue() {
        return value;
    }

    @JsonAnySetter
    public void set(String name, Object value) {
        this.value.put(name, value);
    }

}
