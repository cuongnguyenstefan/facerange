package edu.mum.facerange.backingbean.impl;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import javax.inject.Inject;

import edu.mum.facerange.model.Comment;
import edu.mum.facerange.model.User;
import edu.mum.facerange.service.CommentService;
import edu.mum.facerange.service.impl.CommentServiceImpl;

public class CommentActionListener implements ActionListener {

	@Override
	public void processAction(ActionEvent event) throws AbortProcessingException {
		UIComponent container = event.getComponent().getNamingContainer();
		UIInput commentInput = (UIInput) container.findComponent("commentform:comment");
		String comment = (String) commentInput.getValue();
		int postid = (int) ((UIInput) container.findComponent("commentform:postid")).getValue();

		if (comment == null || comment.isEmpty()) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(container.getClientId(), new FacesMessage("Comment cannot be empty"));
			//throw new AbortProcessingException("Comment is required");
			return;
		}

		Comment objComment = new Comment();
		
		//Get Logged User
		objComment.setUserId(getLogUser().getUserId());
		objComment.setPostId(postid);
		objComment.setComment(comment);

		CommentService service = new CommentServiceImpl();
		service.addComment(objComment);
		commentInput.resetValue();
	}

	public User getLogUser() {
		User user = new User();
		user.setUserId(9);
		user.setFullnane("Tan Luong");
		return user;
	}
}
