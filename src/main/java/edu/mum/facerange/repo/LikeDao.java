package edu.mum.facerange.repo;

import java.util.List;

import edu.mum.facerange.model.Like;

public interface LikeDao {
	public void addLike(Like like);	
	public boolean liked(int postId, int userId);
	public int count(int postId);
}
