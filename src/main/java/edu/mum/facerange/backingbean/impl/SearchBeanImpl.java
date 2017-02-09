package edu.mum.facerange.backingbean.impl;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import edu.mum.facerange.backingbean.SearchBean;
import edu.mum.facerange.model.User;
import edu.mum.facerange.service.UserService;

@Named("searchBean")
@SessionScoped
public class SearchBeanImpl implements SearchBean, Serializable {
	
	private static final long serialVersionUID = -5204026452725841866L;

	private String searchName;
	
	public String getSearchName() {
		return searchName;
	}


	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	private List<User> result;
	
	@Inject
	private UserService userService;

	@Override
	public String search() {
		result = userService.searchUser(searchName);
		return "search?faces-redirect=true";
	}


	public List<User> getResult() {
		return result;
	}

	public void setResult(List<User> result) {
		this.result = result;
	}

}
