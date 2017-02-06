package edu.mum.facerange.repo;

import java.util.List;

import edu.mum.facerange.model.Comment;
import edu.mum.facerange.model.Post;

public interface CommentDao {

	public void addComment(Comment coment);

	public List<Comment> getPostComments(int postId);

	public void deleteComment(int commentId);

	public void updatecomment(Comment comment);

	public Post getComment(int commentId);
	
	public List<Comment> getLastComments(int postId, int lastCommentId);

	public List<Comment> getComments(int offset, int size, int userId);
}
