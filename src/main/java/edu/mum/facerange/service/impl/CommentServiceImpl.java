package edu.mum.facerange.service.impl;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import edu.mum.facerange.model.Comment;
import edu.mum.facerange.repo.CommentDao;
import edu.mum.facerange.repo.impl.CommentDaoImpl;
import edu.mum.facerange.service.CommentService;

@ApplicationScoped
public class CommentServiceImpl implements CommentService {

	//@Inject
	CommentDao commentRepo = new CommentDaoImpl();
	
	@Override
	public void addComment(Comment comment) {
		commentRepo.addComment(comment);
	}

	@Override
	public void deleteComment(int commentId) {
		commentRepo.deleteComment(commentId);
	}

	@Override
	public List<Comment> getPostComments(int postId) {
		return commentRepo.getPostComments(postId);
	}

}
