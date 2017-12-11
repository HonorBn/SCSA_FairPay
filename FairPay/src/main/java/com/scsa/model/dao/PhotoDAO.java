package com.scsa.model.dao;

import java.util.List;

import com.scsa.model.vo.PhotoInfo;

public interface PhotoDAO {

	boolean insertPhoto(PhotoInfo photo);

	boolean updatePhoto(PhotoInfo photo);

	boolean removePhoto(String photoId);

	List<PhotoInfo> selectPhotoList(String eventId);
}
