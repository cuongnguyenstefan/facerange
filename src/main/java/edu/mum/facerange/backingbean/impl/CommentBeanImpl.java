package edu.mum.facerange.backingbean.impl;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import edu.mum.facerange.model.Comment;
import edu.mum.facerange.service.CommentService;

@Named("commentBean")
@RequestScoped
public class CommentBeanImpl {
	
	@Inject
	CommentService commentService;
	
	String comment; 
	
	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public List<Comment> getComments(int postId) {
		return commentService.getPostComments(postId);
	}
}
