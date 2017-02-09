package edu.mum.facerange.service;

import edu.mum.facerange.model.Like;

public interface LikeService {
	void addLike(Like like);
	void deleteLike(int postId, int userId);
	boolean liked(int postId, int userId);
	int count(int postId);
}
