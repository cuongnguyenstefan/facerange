package edu.mum.facerange.service.impl;

import javax.inject.Inject;

import edu.mum.facerange.model.Post;
import edu.mum.facerange.repo.PostDao;
import edu.mum.facerange.service.PostService;

public class PostServiceImpl implements PostService {

	@Inject
	PostDao postRepo;
	
	@Override
	public void addPost(Post post) {
		postRepo.addPost(post);
	}

}
