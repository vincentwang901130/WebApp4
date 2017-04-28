package edu.scs.carleton.comp.ls.view.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import bddTest.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import edu.scs.carleton.comp.ls.dbam.DBLogger;
import edu.scs.carleton.comp.ls.view.beans.Bean;
import edu.scs.carleton.comp.ls.view.beans.CourseBean;
import edu.scs.carleton.comp.ls.view.beans.TermBean;
import edu.scs.carleton.comp.ls.view.beans.UserBean;
import edu.scs.carleton.comp.ls.view.dao.DBCourse;
import edu.scs.carleton.comp.ls.view.domain.Course;
import edu.scs.carleton.comp.ls.view.domain.Term;
import edu.scs.carleton.comp.ls.view.managers.CacheManager;
import edu.scs.carleton.comp.ls.view.utils.IEvent;
import edu.scs.carleton.comp.ls.view.utils.Message;

@ManagedBean
@RequestScoped
@SessionScoped
public class CourseController extends Controller {
	StateSignature ss=new StateSignature();
	Logger l=new Logger();
	public CourseController () {
		listOfObjects = new ArrayList<Bean>();
		selectedObjects = new HashMap<Object, Boolean>();	
	}
	@PostConstruct
	public void init(){
		bean = new CourseBean();
		FacesContext facesContext = FacesContext.getCurrentInstance(); 
		session = (HttpSession) facesContext.getExternalContext().getSession(true);
	}
	
	public String returnToSender () {
		return "returnToSender";
	}
	public Bean getBean () {
		return (CourseBean)bean;
	}

	public void setBean (Bean bean) {
		bean = (CourseBean)bean;
	}
/*public String delete() {
		
		dao = new DBCourse();
		
		for (Object o : selectedObjects.keySet())
        {				
			String isbn = (String)o;
			if (selectedObjects.get(isbn) == true) {
				boolean resultStatus = ((DBCourse)dao).delete("coursecode");
				//logMessage (resultStatus, IEvent.TITLE_DELETE, isbn);
				//DBLogger.getInstance().logEvent(((Integer)session.getAttribute("userid")).intValue(), IEvent.TITLE_DELETE, "Title with ISBN: " + isbn + " deleted by User: " + session.getAttribute("username") + " [" + ((resultStatus) ? "SUCCESS" : "FAIL") + "]");
			}
        }	
		
		dao.destroy();
		
		((CourseBean)bean).setCourseCode(courseCode);("");
		
		refresh("");
		return null;
	}
	*/
	public String submit() {
		//System.out.println("submit() starts");
		listOfObjects.clear();	
		dao = new DBCourse();
		setList (((DBCourse)dao).findall());	
		dao.destroy();
		//System.out.println("submit() ends");
		return null;
	}
	
	private void setList(ArrayList<Object> list) {
		for (Object o : list) {
			Course course = (Course)o;
			CourseBean cBean = new CourseBean();
			cBean.setCourseCode(course.getCourseCode());
			cBean.setCourseName(course.getCourseName());
			//aBean.setCourseCode(course.getTermid());
			cBean.setMeetingTimes(course.getMeetingTimes());
			cBean.setTime(course.getTime());
			cBean.setLocation(course.getLocation());
			listOfObjects.add(cBean);
		//	System.out.println(listOfObjects.size());
		}
		
		/*if (!listOfObjects.isEmpty())
			bean = (CourseBean)listOfObjects.get(0);*/
		
		selectedObjects.clear();
		this.render = !listOfObjects.isEmpty();
		
	}
	
	public String create() {
			ss.getPreData();
			if (!((CourseBean)bean).getCourseCode().equals("") && !((CourseBean)bean).getCourseName().equals(""))
			{
				
				dao = new DBCourse();
				boolean resultStatus=((DBCourse)dao).create(
								 ((CourseBean)bean).getCourseCode()
								,((CourseBean)bean).getCourseName()
								,((CourseBean)bean).getTermid()
								,((CourseBean)bean).getMeetingTimes()
								,((CourseBean)bean).getTime()
								,((CourseBean)bean).getLocation());
				dao.destroy();
				logMessage (resultStatus, IEvent.COURSE_CREATE);
			/*	DBLogger.getInstance().logEvent( (Integer)session.getAttribute("stuNo"),
	                     IEvent.COURSE_CREATE, "COURSE_CREATE: " + ((TermBean)bean).getName()+ " [" + ((resultStatus) ? "SUCCESS" : "FAIL") + "]");
				*/
				refresh();
				
				CacheManager.resetCourses();
			}
			ss.getCurData();
			ss.checkSystemStateChange();
			ss.logStateChange();
			if(ss.systemChange_flag==true){
				ss.outPutPreStateInDetail();
				ss.outPutCurStateInDetail();
			}
			
			return "success";
	}
	
	

	public String refresh () {		
		return submit();
	}
	
	@Override
	protected void logMessage(boolean success, int event) {
		
		Message messages = (Message) session.getAttribute("messages");
		
		if (event == IEvent.COURSE_CREATE) 
			message = "Create Course [" + ((CourseBean)bean).getCourseCode() + " "+ ((CourseBean)bean).getCourseName()+"]";
		else if (event == IEvent.COURSE_CREATE)
			message = "Delete User [" + ((UserBean)bean).getFirstname() + " "+ ((UserBean)bean).getLastname()+"]";
		else if (event == IEvent.EMPTY_SEARCH) 
			message = "No search results to display.";
		else if (event == IEvent.EMPTY_CREATE) 
			message = "Please fill in all fields.";
		messages.setMessage (success,message);
	}
	@Override
	protected String delete() {
		// TODO Auto-generated method stub
		return null;
	}

}
