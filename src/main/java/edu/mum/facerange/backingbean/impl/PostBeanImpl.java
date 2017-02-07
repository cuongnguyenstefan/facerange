package edu.mum.facerange.backingbean.impl;


import javax.enterprise.context.RequestScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import edu.mum.facerange.model.Post;
import edu.mum.facerange.service.PostService;

@Named("post")
@RequestScoped
public class PostBeanImpl {
	
	private int userid = 9;
	
	@Inject
	PostService postService;
	
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String addPost()
	{
		if (!status.isEmpty() && status.length() > 0) {
			Post objPost = new Post();
			objPost.setPost(status);
			objPost.setUserId(userid);
			postService.addPost(objPost);
		}
		
		status = "";
		
		return null;
	}
	
}
