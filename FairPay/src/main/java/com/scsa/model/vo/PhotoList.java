package com.scsa.model.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PhotoList {
	private List<PhotoInfo> photoList;

	public List<PhotoInfo> getPhotoList() {
		return photoList;
	}

	@XmlElement(name="photoInfo")
	public void setPhotoList(List<PhotoInfo> photoList) {
		this.photoList = photoList;
	}
	
	
}
