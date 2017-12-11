package com.scsa.model.vo;

public class PhotoInfo {

	private String photoId;
	private String photoImg;
	private String eventId;
	private String title;
	public PhotoInfo() {
		super();
	}
	public PhotoInfo(String photoImg, String eventId, String title) {
		super();
		this.photoImg = photoImg;
		this.eventId = eventId;
		this.title = title;
	}
	public PhotoInfo(String photoId, String photoImg, String eventId, String title) {
		super();
		this.photoId = photoId;
		this.photoImg = photoImg;
		this.eventId = eventId;
		this.title = title;
	}
	public String getPhotoId() {
		return photoId;
	}
	public void setPhotoId(String photoId) {
		this.photoId = photoId;
	}
	public String getPhotoImg() {
		return photoImg;
	}
	public void setPhotoImg(String photoImg) {
		this.photoImg = photoImg;
	}
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PhotoInfo [getPhotoId()=");
		builder.append(getPhotoId());
		builder.append(", getPhotoImg()=");
		builder.append(getPhotoImg());
		builder.append(", getEventId()=");
		builder.append(getEventId());
		builder.append(", getTitle()=");
		builder.append(getTitle());
		builder.append("]");
		return builder.toString();
	}

	

}
