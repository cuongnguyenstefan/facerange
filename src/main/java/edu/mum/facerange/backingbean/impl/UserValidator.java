package edu.mum.facerange.backingbean.impl;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

import edu.mum.facerange.service.AuthenticationService;

@FacesValidator("edu.mum.facerange.backingbean.impl.UserValidator")
@RequestScoped
public class UserValidator implements Validator {
	
	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object value) throws ValidatorException {
		
		String username=(String) value;
		System.out.println("username1:"+username);
		if(getAuthenService().checkAvailable(username))
		{
			FacesMessage message=new FacesMessage("Username not avalaible");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
	}
	
	public AuthenticationService getAuthenService(){
        FacesContext context = FacesContext.getCurrentInstance();
        Application application = context.getApplication();
        return application.evaluateExpressionGet(context, "#{authenticationService}", AuthenticationService.class);
    }

}
