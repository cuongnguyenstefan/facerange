package edu.mum.facerange.backingbean.impl;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import edu.mum.facerange.model.Comment;
import edu.mum.facerange.service.CommentService;

@Named("commentBean")
@SessionScoped
public class CommentBeanImpl implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

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
	
	public void deleteComment(int commentId) {
		commentService.deleteComment(commentId);
	}
}
