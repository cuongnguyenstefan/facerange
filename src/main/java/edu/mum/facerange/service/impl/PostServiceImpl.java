package edu.mum.facerange.service.impl;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import edu.mum.facerange.model.Post;
import edu.mum.facerange.repo.PostDao;
import edu.mum.facerange.service.PostService;

@ApplicationScoped
public class PostServiceImpl implements PostService {

	@Inject
	PostDao postRepo;
	
	@Override
	public void addPost(Post post) {
		postRepo.addPost(post);
	}

	@Override
	public void deletePost(int postId) {
		postRepo.deletePost(postId);
	}
	
	@Override
	public List<Post> getPosts(int offset, int size, int userId) {
		return postRepo.getPosts(offset, size, userId);
	}

	@Override
	public List<Post> getUserPosts(int userId) {
		return postRepo.getUserPosts(userId);
	}

	

}
