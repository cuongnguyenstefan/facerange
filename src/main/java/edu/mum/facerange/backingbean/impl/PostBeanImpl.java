package edu.mum.facerange.backingbean.impl;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.render.ResponseStateManager;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import edu.mum.facerange.model.Comment;
import edu.mum.facerange.model.Post;
import edu.mum.facerange.model.User;
import edu.mum.facerange.service.CommentService;
import edu.mum.facerange.service.PostService;
import edu.mum.facerange.util.ConvertUtils;

@Named("postBean")
@RequestScoped
public class PostBeanImpl implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	PostService postService;

	@Inject
	CommentService commentService;
	
	AuthenticationBeanImpl authenBean = (AuthenticationBeanImpl) FacesContext.getCurrentInstance().
			getExternalContext().getSessionMap().get("authenticationBean");
	
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	 public int getUserId() {
		 HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
					.getRequest();
		 return ConvertUtils.parseInt(req.getSession().getAttribute("userid"), 0);
	 }
	
//	 public void setUserId(int userId) {
//		 this.userId = userId;
//	 }

	public void onload() {

		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		
		if (!isPostBack())
		{
			int userId = ConvertUtils.parseInt(req.getParameter("userid"), 0);
			req.getSession().setAttribute("userid", userId);	
		}	
		
	}

	/**
	 * to check if the request is a post back request
	 * 
	 * @return
	 */
	public boolean isPostBack() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		return facesContext.getExternalContext().getRequestParameterMap()
				.containsKey(ResponseStateManager.VIEW_STATE_PARAM);
	}

	public User getLogUser() {
		//Get logged user
		//authenBean.getUser()
		User user = new User();
		user.setUserId(9);
		user.setFullnane("Tan Luong");
		return user;
	}

	public User getUser() {
		// HttpServletRequest req =
		// (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		// int userId = ConvertUtils.parseInt(req.getParameter("userid"), 0);
		//
		
		
		if (getUserId() <= 0) {
			return getLogUser();
		}

		User user = new User();
		user.setUserId(getUserId());
		user.setFullnane("Guest User");

		return user;
	}

	public boolean getOwner() {
		return getLogUser().getUserId() == getUser().getUserId();
	}

	public String addPost() {
		if (!status.isEmpty() && status.length() > 0) {
			Post objPost = new Post();
			objPost.setPost(status);
			objPost.setUserId(getUser().getUserId());
			postService.addPost(objPost);
		}

		status = "";

		return null;
	}

	public String deletePost(int postId) {
		postService.deletePost(postId);
		return null;
	}

	public String deleteComment() {
		commentService.deleteComment(10);
		return null;
	}

	public List<Post> getPosts() {
		return postService.getUserPosts(getUser().getUserId());
	}

	public List<Comment> getComments(int postId) {
		return commentService.getPostComments(postId);
	}

	public String countComments(int postId) {
		int count = commentService.count(postId);

		if (count == 0) {
			return "Comment";
		} else if (count == 1) {
			return "1 Comment";
		}

		return count + " Comments";

	}
}
