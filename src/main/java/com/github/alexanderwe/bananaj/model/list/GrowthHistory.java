/**
 * @author alexanderweiss
 * @date 07.12.2015
 */
package com.github.alexanderwe.bananaj.model.list;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.alexanderwe.bananaj.model.Link;

import java.util.List;

/**
 * Class for representing a growth history of a mailChimpList
 * @author alexanderweiss
 *
 */
public class GrowthHistory {

	@JsonProperty
	private String list_id;
    @JsonProperty
	private String month;
    @JsonProperty
	private int existing;
    @JsonProperty
	private int imports;
    @JsonProperty
	private List<Link> _links;

    public String getList_id() {
        return list_id;
    }

    public void setList_id(String list_id) {
        this.list_id = list_id;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getExisting() {
        return existing;
    }

    public void setExisting(int existing) {
        this.existing = existing;
    }

    public int getImports() {
        return imports;
    }

    public void setImports(int imports) {
        this.imports = imports;
    }

    public List<Link> get_links() {
        return _links;
    }

    public void set_links(List<Link> _links) {
        this._links = _links;
    }
}
