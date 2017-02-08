package edu.mum.facerange.service.impl;


import edu.mum.facerange.model.Like;
import edu.mum.facerange.repo.LikeDao;
import edu.mum.facerange.repo.impl.LikeDaoImpl;
import edu.mum.facerange.service.LikeService;

public class LikeServiceImpl implements LikeService{

	LikeDao likeRepo = new LikeDaoImpl();
			
	@Override
	public void addLike(Like like) {
		likeRepo.addLike(like);
	}

	@Override
	public int count(int postId) {
		return likeRepo.count(postId);
	}

	@Override
	public boolean liked(int postId, int userId) {
		return likeRepo.liked(postId, userId);
	}

}
