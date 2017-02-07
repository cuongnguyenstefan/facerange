package edu.mum.facerange.model;

public class SocialMedia {
	private Integer socialmediaId;
	private Integer componentId;
	private String facebookLink;
	private String youtubeLink;
	private String instagramLink;
	private String twitterLink;

	public Integer getSocialmediaId() {
		return socialmediaId;
	}

	public void setSocialmediaId(Integer socialmediaId) {
		this.socialmediaId = socialmediaId;
	}

	public String getFacebookLink() {
		return facebookLink;
	}

	public void setFacebookLink(String facebookLink) {
		this.facebookLink = facebookLink;
	}

	public String getYoutubeLink() {
		return youtubeLink;
	}

	public void setYoutubeLink(String youtubeLink) {
		this.youtubeLink = youtubeLink;
	}

	public String getTwitterLink() {
		return twitterLink;
	}

	public void setTwitterLink(String twitterLink) {
		this.twitterLink = twitterLink;
	}

	public Integer getComponentId() {
		return componentId;
	}

	public void setComponentId(Integer componentId) {
		this.componentId = componentId;
	}

	public String getInstagramLink() {
		return instagramLink;
	}

	public void setInstagramLink(String instagramLink) {
		this.instagramLink = instagramLink;
	}

}
