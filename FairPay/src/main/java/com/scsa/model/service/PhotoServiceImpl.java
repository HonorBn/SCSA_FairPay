package com.scsa.model.service;

import java.util.List;

import com.scsa.model.dao.PhotoDAO;
import com.scsa.model.vo.PhotoInfo;

public class PhotoServiceImpl implements PhotoService {

	private PhotoDAO photoDao;

	public void setPhotoDao(PhotoDAO photoDao) {
		this.photoDao = photoDao;
	}

	public boolean createPhoto(PhotoInfo photo) {
		return photoDao.insertPhoto(photo);
	}

	public boolean updatePhoto(PhotoInfo photo) {
		return photoDao.updatePhoto(photo);
	}

	public boolean deletePhoto(String photoId) {
		return photoDao.removePhoto(photoId);
	}

	public List<PhotoInfo> selectPhotoList(String eventId) {
		return photoDao.selectPhotoList(eventId);
	}

}
