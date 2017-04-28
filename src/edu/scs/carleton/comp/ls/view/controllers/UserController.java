package edu.scs.carleton.comp.ls.view.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import bddTest.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import edu.scs.carleton.comp.ls.dbam.DBLogger;
import edu.scs.carleton.comp.ls.view.domain.*;
import edu.scs.carleton.comp.ls.view.dao.*;
import edu.scs.carleton.comp.ls.view.beans.Bean;
import edu.scs.carleton.comp.ls.view.beans.UserBean;
import edu.scs.carleton.comp.ls.view.utils.IEvent;
import edu.scs.carleton.comp.ls.view.utils.Message;

@Singleton
@ManagedBean
@SessionScoped
public class UserController extends Controller  {

	public UserController () {
		listOfObjects = new ArrayList<Bean>();
		selectedObjects = new HashMap<Object, Boolean>();	
	}
	
	@PostConstruct
	public void init() {
		bean = new UserBean();
		FacesContext facesContext = FacesContext.getCurrentInstance(); 
		session = (HttpSession) facesContext.getExternalContext().getSession(true);
	}
	
	public Bean getBean () {
		return (UserBean)bean;
	}
	
	public void setBean (Bean bean) {
		this.bean = (UserBean)bean;
	}
	
	public String create() 
	{	
		Logger lg=new Logger();
		String stuNo;
		Random randomNumber = new Random();
		stuNo="Stu"+randomNumber.nextInt(100);
		String password="12345";
		String degree=null;
		String timestatus=null;	
		if (!((UserBean)bean).getFirstname().equals("") 
				&& !((UserBean)bean).getLastname().equals("") 
				&& !((UserBean)bean).getSchool().equals("") 
				&& !((UserBean)bean).getBirthdate().equals(""))  
		{
			dao=new DBUser();
		boolean	resultStatus=((DBUser)dao).create (
					              stuNo
					             ,password
					             ,((UserBean)bean).getFirstname()
					             ,((UserBean)bean).getLastname()
					             ,((UserBean)bean).getBirthdate()
					             ,((UserBean)bean).getSchool()
					             ,degree
					             ,timestatus
								);
			dao.destroy();
			Message message=(Message) session.getAttribute("messages");
			
			message.setFeedback(stuNo, password);
			logMessage (resultStatus, IEvent.USER_CREATE);
			/*DBLogger.getInstance().logEvent( (Integer)session.getAttribute("stuNo")
					                       , IEvent.USER_CREATE, "Created User: " + ((UserBean)bean).getFirstname()+" "+ ((UserBean)bean).getLastname() + " [" + ((resultStatus) ? "SUCCESS" : "FAIL") + "]");*/
			
			refresh();
		}
	
		/*	logMessage (false, IEvent.EMPTY_CREATE);
		*/
		
		
		return "success";
		
	}

	public String jump(){
	return null;
	}
	
	public String refresh () {		
		return submit();
	}
	
	/*public String submit() {
		
		dao = new DBUser();
		listOfObjects.clear();
		
		if (!((UserBean)bean).getFirstname().isEmpty())
			setList (((DBUser)dao).findByFirstname(((UserBean)bean).getFirstname()));
		else
		if (!((UserBean)bean).getLastname().isEmpty())
			setList (((DBUser)dao).findByLastname(((UserBean)bean).getLastname()));
		else
		if(!((UserBean)bean).getBirthdate().isEmpty())
			setList(((DBUser)dao).findbyBirthdate(((UserBean)bean).getBirthdate()));
		dao.destroy();
		
		return null;
	}*/
	
	private void setList (List<Object> list) {
		
		for (Object o : list) {
			User user = (User)o; 
			
			UserBean uBean = new UserBean();
			uBean.setFirstname(user.getFirstname()); 
			uBean.setLastname(user.getLastname());
			uBean.setBirthdate(user.getSchool());
			uBean.setSchool(user.getSchool());
			listOfObjects.add(uBean);
		}
		
		if (listOfObjects.size() != 0) {
			bean = listOfObjects.get(0);
		}
		else
		//	logMessage (false, IEvent.EMPTY_SEARCH);
		
		selectedObjects.clear();		
		this.render = !listOfObjects.isEmpty();
	}

	@Override
	protected String delete() {
		// TODO Auto-generated method stub
		return null;
	}

	
	/*public String delete  () {

		dao = new DBUser();
		
		for (Object o : selectedObjects.keySet())
        {			
			String userName = (String)o;
			boolean resultStatus = ((DBUser)dao).delete(userName);
			logMessage (resultStatus, IEvent.USER_DELETE);
			DBLogger.getInstance().logEvent( ((Integer)session.getAttribute("userid")).intValue()
										   , IEvent.USER_DELETE, "Deleted User: " + ((UserBean)bean).getUserName() + " [" + ((resultStatus) ? "SUCCESS" : "FAIL") + "]"); 
        }
		
		dao.destroy();
		
		refresh();
		return "success";
	}*/

	public void message(boolean success){
	Message messages=(Message)session.getAttribute("messages");
	messages.setMessage(success, message);
	}

	@Override
	protected String submit() {
		// TODO Auto-generated method stub
		return null;
	}
	
		protected void logMessage (boolean success, int event) {
		Logger lg=new Logger();
		Message messages = (Message) session.getAttribute("messages");
	
		if (event == IEvent.USER_CREATE) 
			{message = "Create User [" + ((UserBean)bean).getFirstname() + " "+ ((UserBean)bean).getLastname()+"]";
			lg.output("valid", message);}
		else if (event == IEvent.USER_DELETE)
			{message = "Delete User [" + ((UserBean)bean).getFirstname() + " "+ ((UserBean)bean).getLastname()+"]";
			lg.output("valid", message);
			}
		else if (event == IEvent.EMPTY_SEARCH) 
			{message = "No search results to display.";
			lg.output("invalid", message);
			}
		else if (event == IEvent.EMPTY_CREATE) 
			{message = "Please fill in all fields.";
			lg.output("invalid", message);
			}
		messages.setMessage (success,message);
		
	}
		
	}
		

