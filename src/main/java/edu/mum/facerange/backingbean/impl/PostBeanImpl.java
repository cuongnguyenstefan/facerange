package edu.mum.facerange.backingbean.impl;


import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import edu.mum.facerange.model.Post;
import edu.mum.facerange.model.User;
import edu.mum.facerange.service.PostService;

@Named("postBean")
@SessionScoped
public class PostBeanImpl implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int userId = 9;
	
	@Inject
	PostService postService;
	
	//UserBean user = (UserBean) request.getSession().getAttribute("user");	
	
	private String status;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	public User getUser() {
		User user = new User();
		user.setUserId(9);
		user.setFullnane("Tan Luong");
		return user;
	}
	
	public String addPost()
	{
		if (!status.isEmpty() && status.length() > 0) {
			Post objPost = new Post();
			objPost.setPost(status);
			objPost.setUserId(userId);
			postService.addPost(objPost);
		}
		
		status = "";
		
		return null;
	}
	
	public String deletePost(int postId) {
		postService.deletePost(postId);
		return null;
	}
	
	public List<Post> getPosts() {
		return postService.getUserPosts(userId);
	}
}
