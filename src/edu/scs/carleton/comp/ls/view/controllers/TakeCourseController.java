package edu.scs.carleton.comp.ls.view.controllers;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;






import edu.scs.carleton.comp.ls.dbam.DBLogger;
//import edu.scs.carleton.comp.ls.dbam.DBLogger;
import edu.scs.carleton.comp.ls.view.beans.Bean;
import edu.scs.carleton.comp.ls.view.beans.CourseBean;
import edu.scs.carleton.comp.ls.view.beans.StuCourseBean;
import edu.scs.carleton.comp.ls.view.dao.DBCourse;
import edu.scs.carleton.comp.ls.view.dao.DBStuCourse;
import edu.scs.carleton.comp.ls.view.domain.Course;
import edu.scs.carleton.comp.ls.view.domain.StuCourse;
import edu.scs.carleton.comp.ls.view.managers.CacheManager;
import edu.scs.carleton.comp.ls.view.utils.IEvent;
import edu.scs.carleton.comp.ls.view.utils.Message;

@Singleton
@ManagedBean
@SessionScoped
public class TakeCourseController extends Controller{
	

	public TakeCourseController () {
		listOfObjects = new ArrayList<Bean>();
		selectedObjects = new HashMap<Object, Boolean>();
		listOfObjectsA=new ArrayList<Bean>();
		selectedObjectsA=new HashMap<Object, Boolean>();
	}
	@PostConstruct
	public void init(){
		bean = new CourseBean();
		beanA = new StuCourseBean();
		FacesContext facesContext = FacesContext.getCurrentInstance(); 
		session = (HttpSession) facesContext.getExternalContext().getSession(true);
	}
	
	public Bean getBean () {
		return (CourseBean)bean;
	}
	public Bean getBeanA () {
		return (StuCourseBean)beanA;
	}
	
	public void setBean (Bean bean){
		this.bean = (CourseBean)bean;
	}
	public void setBeanA (Bean beanA){
			this.beanA = (StuCourseBean)beanA;
		}
		
		
	public String create () {
		//System.out.println(selectedObjects.size());
		daoA=new DBStuCourse();
		for (Object o : selectedObjects.keySet())
        {			
			String courseCode = (String)o;
			if(selectedObjects.get(o)==true){
				boolean resultStatus = ((DBStuCourse)daoA).create ( 
					session.getAttribute("stuNo").toString(),
					courseCode,
					null,
					((StuCourseBean)beanA).getTermid()
			);
			
			logMessage (resultStatus, IEvent.COURSE_CREATE);
			//DBLogger.getInstance().logEvent((Integer)session.getAttribute("courseCode"), IEvent.COURSE_CREATE, "Course: " + ((StuCourseBean)scbean).getCourse() + " [" + ((resultStatus) ? "SUCCESS" : "FAIL") + "]");
		
			//refresh();
			}
			
		}
		dao.destroy();
		refresh();
		return "success";
	}
	
	/*public String search(){
		listOfObjects.clear();
		dao = new DBCourse();
		setList(((DBCourse)dao).findall());
		dao.destroy();	
		return null;
	}*/
	
	public String search(){
		//show course of the term
		listOfObjects.clear();
		dao = new DBCourse();
		setList(((DBCourse)dao).findall());
		dao.destroy();
		
		//show my course
		listOfObjectsA.clear();
		daoA=new DBStuCourse();
		setListA(((DBStuCourse)daoA).findAll());
		daoA.destroy();
		return null;
	}
	
	public String showmycourse(){
		listOfObjectsA.clear();
		daoA=new DBStuCourse();
		setListA(((DBStuCourse)daoA).findall());
		daoA.destroy();
		return null;
		
	}
	public String delete() {
		daoA = new DBStuCourse();
		for (Object o : selectedObjectsA.keySet())
        {			
			String courseCode = (String)o;
			String stuNo=session.getAttribute("stuNo").toString();
			if(selectedObjectsA.get(o)==true){
				boolean resultStatus = ((DBStuCourse)daoA).delete(courseCode, stuNo);
			logMessage (resultStatus, IEvent.COURSE_DELETE);
				DBLogger.getInstance().logEvent( (Integer)session.getAttribute("stuNo")
										   , IEvent.COURSE_DELETE, "Deleted COURSE: " + ((StuCourseBean)beanA).getCourseCode() + " [" + ((resultStatus) ? "SUCCESS" : "FAIL") + "]");
			}
			 
        }
		
		daoA.destroy();
		
		refresh();
		return "success";
	}
	
	
	
	
	
	public void setListA(List<Object> list) {
		// TODO Auto-generated method stub
		for (Object o : list) {
			StuCourse stucourse = (StuCourse)o; 
			StuCourseBean aBean = new StuCourseBean();
			aBean.setStuNo(stucourse.getStuNo());
			aBean.setCourseCode(stucourse.getCourseCode());
			aBean.setRegisterDate(stucourse.getRegisterDate());
			aBean.setTermid(stucourse.getTermid());

			listOfObjectsA.add(aBean);
		}
		
		if (listOfObjectsA.size() != 0) {
			beanA = listOfObjectsA.get(0);
		}
		else
			logMessage (false, IEvent.EMPTY_SEARCH);
		
		selectedObjectsA.clear();		
		this.render = !listOfObjectsA.isEmpty();
	}
	
	public void takeCourse(){
		create ();
		submit();
	}
	
	public void dropCourse(){
		delete();
	}
	
	public String refresh () {		
		//return null;
		return submit();
	}
	
	private void refreshCacheManager () 
	{
		CacheManager.resetTerms();
	}
	
	public String submit() {
	
		return null;
	}
	
	private void setList (List<Object> list) {
		
		for (Object o : list) {
			Course course = (Course)o; 
			
			CourseBean cBean = new CourseBean();
			cBean.setCourseCode(course.getCourseCode());
			cBean.setCourseName(course.getCourseName());
			cBean.setTermid(course.getTermid());
			cBean.setMeetingTimes(course.getMeetingTimes());
			cBean.setTime(course.getTime());
			cBean.setLocation(course.getLocation());
			listOfObjects.add(cBean);
		}
		
		if (listOfObjects.size() != 0) {
			bean = listOfObjects.get(0);
		}
		else
			logMessage (false, IEvent.EMPTY_SEARCH);
		
		selectedObjects.clear();		
		this.render = !listOfObjects.isEmpty();
	}
	
	
	

	protected void logMessage (boolean success, int event) {
		
		Message messages = (Message) session.getAttribute("messages");
	
		if (event == IEvent.COURSE_CREATE) 
			message = "Create Course [" + ((CourseBean)bean).getCourseCode() + "]";
		else if (event == IEvent.COURSE_DELETE) 
			message = "Delete Course [" + session.getAttribute("courseCode") + "]";
		else if (event == IEvent.EMPTY_SEARCH) 
			message = "No search results to display.";
		else if (event == IEvent.EMPTY_CREATE) 
			message = "Please fill in all fields.";

		messages.setMessage (success,message);		
	}

	
	
	
}
