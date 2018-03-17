package com.github.alexanderwe.bananaj.model.campaign;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.alexanderwe.bananaj.model.Link;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SendChecklist {


    @JsonProperty
    private boolean is_ready;
    @JsonProperty
    private List<Item> items;
    @JsonProperty
    List<Link> _links;

    public boolean isIs_ready() {
        return is_ready;
    }

    public void setIs_ready(boolean is_ready) {
        this.is_ready = is_ready;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<Link> get_links() {
        return _links;
    }

    public void set_links(List<Link> _links) {
        this._links = _links;
    }

    private class Item {

        @JsonProperty
        private ItemType type;
        @JsonProperty
        private int id;
        @JsonProperty
        private String heading;
        @JsonProperty
        private String deatils;

        public ItemType getType() {
            return type;
        }

        public void setType(ItemType type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getHeading() {
            return heading;
        }

        public void setHeading(String heading) {
            this.heading = heading;
        }

        public String getDeatils() {
            return deatils;
        }

        public void setDeatils(String deatils) {
            this.deatils = deatils;
        }
    }

    private enum ItemType {

        @JsonProperty("success") SUCCESS,
        @JsonProperty("warning") WARNING,
        @JsonProperty("error") ERROR,
    }

}
