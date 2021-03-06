package edu.mum.facerange.backingbean.impl;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.Application;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;

import edu.mum.facerange.model.Comment;
import edu.mum.facerange.model.User;
import edu.mum.facerange.service.CommentService;
import edu.mum.facerange.service.impl.CommentServiceImpl;

@Named("commentBean")
@RequestScoped
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
	
	public void addComment(AjaxBehaviorEvent event){
	    
	    UIComponent container = event.getComponent().getNamingContainer();
	    
		UIInput commentInput = (UIInput) container.findComponent("commentform:comment");
		String comment = (String) commentInput.getValue();
		
		int postid = (int) ((UIInput) container.findComponent("commentform:postid")).getValue();

		if (comment == null || comment.isEmpty()) {
			//FacesContext context = FacesContext.getCurrentInstance();
			//context.addMessage(container.getClientId(), new FacesMessage("Comment cannot be empty"));
			//throw new AbortProcessingException("Comment is required");
			return;
		}

		Comment objComment = new Comment();		
		
		
		objComment.setUserId(getLoggedUser().getUserId());
		objComment.setPostId(postid);
		objComment.setComment(comment);

		CommentService service = new CommentServiceImpl();
		service.addComment(objComment);
		commentInput.resetValue();
	}
	
	public AuthenticationBeanImpl getAuthenBean(){
        FacesContext context = FacesContext.getCurrentInstance();
        Application application = context.getApplication();
        return application.evaluateExpressionGet(context, "#{authenticationBean}", AuthenticationBeanImpl.class);
    }
	
	public User getLoggedUser() {
		return getAuthenBean().getUser();
	}
}
