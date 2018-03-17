package com.github.alexanderwe.bananaj.model.campaign;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.alexanderwe.bananaj.model.Link;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Feedback {

    @JsonProperty
    private List<FeedbackMessage> feedback;
    @JsonProperty
    private String campaign_id;
    @JsonProperty
    private int total_items;
    @JsonProperty
    private List<Link> _links;

    public List<FeedbackMessage> getFeedback() {
        return feedback;
    }

    public void setFeedback(List<FeedbackMessage> feedback) {
        this.feedback = feedback;
    }

    public String getCampaign_id() {
        return campaign_id;
    }

    public void setCampaign_id(String campaign_id) {
        this.campaign_id = campaign_id;
    }

    public int getTotal_items() {
        return total_items;
    }

    public void setTotal_items(int total_items) {
        this.total_items = total_items;
    }

    public List<Link> get_links() {
        return _links;
    }

    public void set_links(List<Link> _links) {
        this._links = _links;
    }


    public static class FeedbackMessage {
        @JsonProperty
        private int feedback_id;
        @JsonProperty
        private int parent_id;
        @JsonProperty
        private int block_id;
        @JsonProperty
        private String message;
        @JsonProperty
        private boolean is_complete;
        @JsonProperty
        private String created_by;
        @JsonProperty
        private LocalDateTime created_at;
        @JsonProperty
        private LocalDateTime updated_at;
        @JsonProperty
        private String source;
        @JsonProperty
        private String campaign_id;

        public int getFeedback_id() {
            return feedback_id;
        }

        public void setFeedback_id(int feedback_id) {
            this.feedback_id = feedback_id;
        }

        public int getParent_id() {
            return parent_id;
        }

        public void setParent_id(int parent_id) {
            this.parent_id = parent_id;
        }

        public int getBlock_id() {
            return block_id;
        }

        public void setBlock_id(int block_id) {
            this.block_id = block_id;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public boolean isIs_complete() {
            return is_complete;
        }

        public void setIs_complete(boolean is_complete) {
            this.is_complete = is_complete;
        }

        public String getCreated_by() {
            return created_by;
        }

        public void setCreated_by(String created_by) {
            this.created_by = created_by;
        }

        public LocalDateTime getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            OffsetDateTime offsetDateTime = OffsetDateTime.parse(created_at);
            this.created_at = offsetDateTime.toLocalDateTime();
        }

        public LocalDateTime getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            OffsetDateTime offsetDateTime = OffsetDateTime.parse(updated_at);
            this.updated_at = offsetDateTime.toLocalDateTime();
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getCampaign_id() {
            return campaign_id;
        }

        public void setCampaign_id(String campaign_id) {
            this.campaign_id = campaign_id;
        }

        public List<Link> get_links() {
            return _links;
        }

        public void set_links(List<Link> _links) {
            this._links = _links;
        }

        @JsonProperty
        private List<Link> _links;


    }
}
