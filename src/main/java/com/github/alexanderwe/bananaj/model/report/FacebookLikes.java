/**
 * @author alexanderweiss
 * @date 12.12.2015
 */
package com.github.alexanderwe.bananaj.model.report;


import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class for representing facebook likes
 * @author alexanderweiss
 *
 */
public class FacebookLikes {

	@JsonProperty
	private int recipient_likes;
	@JsonProperty
	private int unique_likes;
	@JsonProperty
	private int facebook_likes;

	public int getRecipient_likes() {
		return recipient_likes;
	}

	public void setRecipient_likes(int recipient_likes) {
		this.recipient_likes = recipient_likes;
	}

	public int getUnique_likes() {
		return unique_likes;
	}

	public void setUnique_likes(int unique_likes) {
		this.unique_likes = unique_likes;
	}

	public int getFacebook_likes() {
		return facebook_likes;
	}

	public void setFacebook_likes(int facebook_likes) {
		this.facebook_likes = facebook_likes;
	}
}
