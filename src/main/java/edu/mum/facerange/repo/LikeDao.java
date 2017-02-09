package edu.mum.facerange.repo;

import edu.mum.facerange.model.Like;

public interface LikeDao {
	public void addLike(Like like);	
	public void deleteLike(int postId, int userId);
	public boolean liked(int postId, int userId);
	public int count(int postId);
}
