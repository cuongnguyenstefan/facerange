package edu.mum.facerange.repo;

import java.util.List;

import edu.mum.facerange.model.Post;

public interface PostDao {
	public void addPost(Post post);

	public List<Post> getUserPosts(int userId);

	public void deletePost(int postId);

	public void updatePost(Post post);

	public Post getPost(int postId);

	public List<Post> getPosts(int offset, int size, int userId);

}
