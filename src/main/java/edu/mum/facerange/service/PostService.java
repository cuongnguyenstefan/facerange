package edu.mum.facerange.service;

import java.util.List;

import edu.mum.facerange.model.Post;

public interface PostService {
	void addPost(Post post);
	void deletePost(int postId);
	List<Post> getUserPosts(int userId);
	List<Post> getPosts(int offset, int size, int userId);
}
