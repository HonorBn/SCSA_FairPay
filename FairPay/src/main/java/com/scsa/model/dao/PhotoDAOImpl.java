package com.scsa.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.scsa.model.vo.PhotoInfo;

public class PhotoDAOImpl implements PhotoDAO {

	private SqlSession sqlSession;

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public boolean insertPhoto(PhotoInfo photo) {
		return sqlSession.insert("photo.insertPhoto", photo) == 1 ? true : false;
	}

	public boolean updatePhoto(PhotoInfo photo) {
		return sqlSession.update("photo.updatePhoto", photo) == 1 ? true : false;
	}

	public boolean removePhoto(String photoId) {
		return sqlSession.delete("photo.deletePhoto", photoId) == 1 ? true : false;
	}

	public List<PhotoInfo> selectPhotoList(String eventId) {
		return sqlSession.selectList("photo.selectPhotoListByEventId", eventId);
	}

}
