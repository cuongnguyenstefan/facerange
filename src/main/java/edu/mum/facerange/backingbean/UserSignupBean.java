//package edu.mum.facerange.backingbean;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.io.Serializable;
//
//import javax.enterprise.context.SessionScoped;
//import javax.faces.application.FacesMessage;
//
//import javax.faces.bean.ViewScoped;
//import javax.faces.component.UIComponent;
////import javax.faces.component.UIComponent;
//import javax.faces.context.FacesContext;
//
//import javax.inject.Inject;
//import javax.inject.Named;
//
//import org.apache.commons.io.IOUtils;
//
//import org.primefaces.model.UploadedFile;
//
//import edu.mum.facerange.model.User;
//import edu.mum.facerange.repo.ImageStoreDao;
//import edu.mum.facerange.service.AuthenticationService;
//
//
//@Named("userbean")
//@SessionScoped
//public class UserSignupBean implements Serializable {
//	private static final long serialVersionUID = 1L;
//	private UploadedFile profilepic;
//	@Inject AuthenticationService auth;
//	private User  loginUser;
//	String userName;
//	String password;
//	@Inject
//	private ImageStoreDao imageStoreDao;
//	
//	
////	//one
////	 private UIComponent userInput;
////	 public void  checkuser(){
////		 System.out.println("hello");
////			if(auth.checkAvailable(user.getUserName())){
////				
////				FacesContext context = FacesContext.getCurrentInstance();
////	            context.addMessage(userInput.getClientId(), new FacesMessage(FacesMessage.SEVERITY_ERROR, "unvalaible username", null));
////				
////			}
////		
////		}
////	public UIComponent getUserInput() {
////		return userInput;
////	}
////	
////	public void setUserInput(UIComponent userInput) {
////		this.userInput = userInput;
////	}
////					ui part
//	
////	<!--  <p:inputText id="userName" value="#{userbean.user.userName}" required="true"
////			label="User Name" title="Enter your User Name!" binding="#{userbean.userInput}">
////			<f:ajax event="blur" listener="#{userbean.checkuser}" update="userNameMsg"  global="false"/>
////			-->
//	
//	
//	String availaible;
//	public User getLoginUser() {
//		return loginUser;
//	}
//
//	
//	
//	public void setLoginUser(User loginUser) {
//		this.loginUser = loginUser;
//	}
//
//	public String getUserName() {
//		return userName;
//	}
//
//	public void setUserName(String userName) {
//		this.userName = userName;
//	}
//
//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}
//	
//	User user=new User();
//	public UploadedFile getProfilepic() {
//		return profilepic;
//	}
//
//	public void setProfilepic(UploadedFile profilepic) {
//		this.profilepic = profilepic;
//	}
//	
//	public void  signin()
//	{
//		loginUser=	auth.signin(this.userName,this.password);
//		if(loginUser!=null)
//		{
//			System.out.println(loginUser.getFullName()+" "+loginUser.getEmail()+" "+loginUser.getPassword());
//			try {
//				FacesContext.getCurrentInstance().getExternalContext().dispatch("index");
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		else
//		{
//			System.out.println("null");
//			try {
//				FacesContext.getCurrentInstance().getExternalContext().redirect("login1.jsf");
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//		}
//	}
//	
//	
//	
//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user1) {
//		this.user = user1;
//	}
//
//	
//	//checking for available user
//	
//	
//
//	
//
//
////	
////	public void  checkAvailableuser(ValueChangeEvent enven)//to check if user name is avalaible
////	{
////		
////	}
//	//saving file
//	public void savePicture() throws IOException {
//	    String filename = profilepic.getFileName();
//	    
//	    InputStream input = profilepic.getInputstream();
//	    OutputStream output = new FileOutputStream(new File(filename));
//	    user.setPicture(filename);
//
//	    try {
//	        IOUtils.copy(input, output);
//	    } finally {
//	        IOUtils.closeQuietly(input);
//	        IOUtils.closeQuietly(output);
//	    }
//	}
//	public String  addUser() 
//	{
//		try {
//			//add the 
//			int saveImage = imageStoreDao.saveImage(profilepic.getInputstream());
//			savePicture();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		auth.addUser(this.user);
//		return "login1";
//		
//	}
//		
//	}
