package edu.mum.facerange.service;

import java.util.List;

import edu.mum.facerange.model.Comment;

public interface CommentService {
	void addComment(Comment comment);
	void deleteComment(int commentId);
	List<Comment> getPostComments(int postId);
}
