package edu.mum.facerange.backingbean.impl;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

import edu.mum.facerange.model.Comment;
import edu.mum.facerange.service.CommentService;
import edu.mum.facerange.service.impl.CommentServiceImpl;

public class CommentActionListener implements ActionListener {

	@Override
	public void processAction(ActionEvent event) throws AbortProcessingException {
		UIComponent container = event.getComponent().getNamingContainer();
		String comment = (String) ((UIInput) container.findComponent("commentform:comment")).getValue();
		int postid = (int) ((UIInput) container.findComponent("commentform:postid")).getValue();

		if (comment == null || comment.isEmpty()) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(container.getClientId(), new FacesMessage("Comment cannot be empty"));
			throw new AbortProcessingException("Comment is required");
		}

		Comment objComment = new Comment();
		objComment.setUserId(9);
		objComment.setPostId(postid);
		objComment.setComment(comment);

		CommentService service = new CommentServiceImpl();
		service.addComment(objComment);
	}

}
