package edu.mum.facerange.service;

public interface PageService {
	
	// load information of a page
	public boolean load();
	
	// post a new status
	public boolean post();
	
	// when user like something
	public boolean like();
	
	// add comment on a status
	public boolean comment();
	
	// for user to get their list of components - can be used in loading page and edit page
	public boolean getComponents();
	
	// for user to update their components.
	public boolean updateComponents();
	
	// give in post id, last comment timestamp
	public boolean getNewComments();

}
