package com.github.alexanderwe.bananaj.model.list.member;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

public class Note {

    @JsonProperty
    private int note_id;
    @JsonProperty
    private LocalDateTime created_at;
    @JsonProperty
    private String created_by;
    @JsonProperty
    private String note;

    public int getNote_id() {
        return note_id;
    }

    public void setNote_id(int note_id) {
        this.note_id = note_id;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
