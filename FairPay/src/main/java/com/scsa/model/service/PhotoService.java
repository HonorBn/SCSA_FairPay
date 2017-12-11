package com.scsa.model.service;

import java.util.List;

import com.scsa.model.vo.PhotoInfo;

public interface PhotoService {

	boolean createPhoto(PhotoInfo photo);

	boolean updatePhoto(PhotoInfo photo);

	boolean deletePhoto(String photoId);

	List<PhotoInfo> selectPhotoList(String eventId);

}
