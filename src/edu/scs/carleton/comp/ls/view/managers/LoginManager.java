package edu.scs.carleton.comp.ls.view.managers;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.ejb.Singleton;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;




//import edu.scs.carleton.comp.ls.admin.Role;
import edu.scs.carleton.comp.ls.view.domain.User;
import edu.scs.carleton.comp.ls.view.dao.DBUser;
import edu.scs.carleton.comp.ls.view.beans.BakedBean;

import edu.scs.carleton.comp.ls.view.utils.Message;


@Singleton
@ManagedBean
@RequestScoped
public class LoginManager {
	private String message = "";
	private String password;
	private String stuNo;
	private HttpSession session;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String today = sdf.format(Calendar.getInstance().getTime());
	private String message2=today;
	public LoginManager () { 
		
    }
	
    public final String getStuNo() {
		return stuNo;
	}
   
	public final void setStuNo(String stuNo) {
		this.stuNo = stuNo;
	}
	public final String getMessage() {
		return message;
	}
	public final String getMessage2(){
		return message2;
	}
	public final String getPassword() {
		return password;
	}
	
	//public final void setUsername(String username) {
		//this.username = username;
	//}

	public final void setPassword(String password) {
		this.password = password;
	}
	
	
	public String login () {
		clearErrorMessages();
		DBUser dbUser = new DBUser();
		User user=(User)dbUser.findByPrimaryKey(stuNo);
		dbUser.destroy();
		if (user == null) {
			message = "Invalid stuNo. Please try again.";
			return "fail";
			}		
		if (!password.equals(user.getPassword())) {						
			message = "Invalid Password. Please try again.";			    
			return "fail";	
		}
		message = "success";
		(new BakedBean()).bakeBeans();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		session = (HttpSession) facesContext.getExternalContext().getSession(true);
		session.setAttribute("stuNo", user.getStuNo());
	/*	CacheManager.resetCourses();
		CacheManager.resetTerms();*/
		if(user.getStuNo().equals("admin"))
		{
		session.setAttribute("librarian", true);
		return "librarian";
		
		}
		else
		session.setAttribute("admin", false);
			return "public-member";
	}
	
		/*(new BakedBean()).bakeBeans();
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		session = (HttpSession) facesContext.getExternalContext().getSession(true);
		
		session.setAttribute("userid", user.getUserId());
		session.setAttribute("username", user.getUsername());
		session.setAttribute("roleid", user.getRole().getRoleId());
		
		if (user.getRole().getRoleId() == Role.LIBRARIAN) {
			session.setAttribute("librarian", true);
			return "librarian";
		}
			
		session.setAttribute("librarian", false);
		
		return "public-member";
	}
	*/
	public String logOut(){
		return "fail";
	}
	private void clearErrorMessages () {
		try {
			
			Message messages = (Message) session.getAttribute("messages");
			
			messages.clear();
		} catch ( Exception e) {
			//Debug.trace(this,"clearErrorMessagers",e.getLocalizedMessage());
		}
	}
	
	public void finalize () throws Throwable {
		super.finalize();
	}
	public String register(){
		
		return "newstudent";
	}
}
