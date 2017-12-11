package com.scsa.model.vo;

public class PhotoInfo {

	private String photoId;
	private String photoImg;
	private String meetingId;
	private String title;

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

	public String getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(String meetingId) {
		this.meetingId = meetingId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public PhotoInfo(String photoId, String photoImg, String meetingId, String title) {
		super();
		this.photoId = photoId;
		this.photoImg = photoImg;
		this.meetingId = meetingId;
		this.title = title;
	}

	public PhotoInfo() {
		super();
	}

	@Override
	public String toString() {
		return "PhotoInfo [photoId=" + photoId + ", photoImg=" + photoImg + ", meetingId=" + meetingId + ", title="
				+ title + "]";
	}

}
