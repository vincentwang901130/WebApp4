package edu.scs.carleton.comp.ls.view.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import bddTest.Logger;
import edu.scs.carleton.comp.ls.dbam.DBLogger;
import edu.scs.carleton.comp.ls.view.beans.Bean;
import edu.scs.carleton.comp.ls.view.beans.TermBean;
import edu.scs.carleton.comp.ls.view.dao.DBTerm;
import edu.scs.carleton.comp.ls.view.domain.Term;
import edu.scs.carleton.comp.ls.view.managers.CacheManager;
import edu.scs.carleton.comp.ls.view.utils.IEvent;
import edu.scs.carleton.comp.ls.view.utils.IEvent;
import edu.scs.carleton.comp.ls.view.utils.Message;

@Singleton
@ManagedBean
@SessionScoped
public class TermController extends Controller{
	public TermController () {
		listOfObjects = new ArrayList<Bean>();
		selectedObjects = new HashMap<Object, Boolean>();	
	
		
		/*listOfObjectsA=new ArrayList<Bean>();*/
			
	}

	@PostConstruct
	public void init(){
		bean = new TermBean();
		/*beanA =new TermBean();*/
		FacesContext facesContext = FacesContext.getCurrentInstance(); 
		session = (HttpSession) facesContext.getExternalContext().getSession(true);
	}
	
	
	DateFormat f=new SimpleDateFormat("yyyy-MM-dd");
	Calendar calendar = Calendar.getInstance();
	
	boolean noOverlapping;
	boolean noTimeConflict;
	
	public Bean getBean () {
		return (TermBean)bean;
	}
	public void setBean (Bean bean){
		this.bean = (TermBean)bean;
	}
	
	/*public Bean getBeanA(){
		return (TermBean)beanA;
	}
	public void setBeanA(Bean beanA){
		this.beanA=(TermBean)beanA;
	}
	*/
	
	public boolean update() throws ParseException{
		dao=new DBTerm();
		Object o=((DBTerm)dao).findByName(((TermBean)bean).getName());
		Term term=(Term)o;	
		listOfObjectsA.add(bean);	
		if(((f.parse(term.getStartDate())).after(f.parse(((TermBean)bean).getEnrollStart())))
				&&((f.parse(term.getEndDate())).after(f.parse(((TermBean)bean).getEnrollEnd())))){
			boolean resultStatus= ((DBTerm)dao).update(
					((TermBean)bean).getName(),
					((TermBean)bean).getEnrollStart(),
					((TermBean)bean).getEnrollEnd());	
		//	((DBTerm)dao).update(((TermBean)bean).getName(),((TermBean)bean).getDropDeadline());	
					
			CacheManager.resetTerms();
			logMessage (resultStatus, IEvent.TERM_SET);	
		}else{
			logMessage (false, IEvent.TERM_SET);
		}
		
		return false;
	}
	public String submit() {
		 {
			 bean.clear();
			listOfObjects.clear();
				dao = new DBTerm();
			    setList(((DBTerm)dao).findall());
				dao.destroy();
				return null;
			}
			
		}
	
	private void setList (ArrayList<Object> list) {

		for (Object o : list) {
			Term term = (Term)o;
			TermBean aBean = new TermBean();
			aBean.setName (term.getName());
			aBean.setStartDate (term.getStartDate());
			aBean.setEndDate(term.getEndDate());
			aBean.setEnrollStart(term.getEnrollStart());
			aBean.setEnrollEnd(term.getEnrollEnd());
			aBean.setDropDeadline(term.getDropDeadline());
			listOfObjects.add(aBean);
		}
	/*	if (!listOfObjects.isEmpty())
			bean = (TermBean)listOfObjects.get(0);*/
		logMessage (false, IEvent.EMPTY_SEARCH);
		selectedObjects.clear();
		this.render = !listOfObjects.isEmpty();
	}
		
	public String create () throws ParseException
         {
		dao=new DBTerm();
		 
		if ((!((TermBean)bean).getName().equals("") 
			&& !((TermBean)bean).getStartDate().equals("") 
			&& !((TermBean)bean).getEndDate().equals(""))
			)
		{	noOverlapping=dateValidate(((DBTerm)dao).findall(), 
				((TermBean)bean).getStartDate(), 
				((TermBean)bean).getEndDate());
		
					if(((TermBean)bean).getEnrollStart().equals(""))
					{
						Date startDate=f.parse(((TermBean)bean).getStartDate());
						calendar.setTime(startDate);
						calendar.add(Calendar.DATE,-30);
						((TermBean)bean).setEnrollStart(f.format(calendar.getTime()));
					}
				
				if(((TermBean)bean).getEnrollEnd().equals(""))
					{
						Date startDate=f.parse(((TermBean)bean).getStartDate());
						calendar.setTime(startDate);
						calendar.add(Calendar.DATE,30);
						((TermBean)bean).setEnrollEnd(f.format(calendar.getTime()));
					}
				
				if(((TermBean)bean).getDropDeadline().equals(""))
					{
						Date endDate=f.parse(((TermBean)bean).getEndDate());
						calendar.setTime(endDate);
						calendar.add(Calendar.DATE,-7);
						((TermBean)bean).setDropDeadline(f.format(calendar.getTime()));
					}
					
				if(noOverlapping){
					
					noTimeConflict=((f.parse(((TermBean)bean).getStartDate())).after(f.parse(((TermBean)bean).getEnrollStart())))
							&&((f.parse(((TermBean)bean).getEndDate())).after(f.parse(((TermBean)bean).getEnrollEnd())))
							&&((f.parse(((TermBean)bean).getEnrollStart())).before(f.parse(((TermBean)bean).getEnrollEnd())));
							if(noTimeConflict)
							{
					dao = new DBTerm();
					boolean resultStatus=((DBTerm)dao).create( ((TermBean)bean).getName(), ((TermBean)bean).getStartDate(), ((TermBean)bean).getEndDate(),((TermBean)bean).getEnrollStart(), ((TermBean)bean).getEnrollEnd(), ((TermBean)bean).getDropDeadline());
					dao.destroy();
					
					if(resultStatus){
						CacheManager.resetTerms();
					}
					
					logMessage (resultStatus, IEvent.TERM_CREATE);
					/*DBLogger.getInstance().logEvent( (Integer)session.getAttribute("stuNo")
			                       , IEvent.TERM_CREATE, "Created Term: " + ((TermBean)bean).getName()+ " [" + ((resultStatus) ? "SUCCESS" : "FAIL") + "]");*/
					refresh();
						bean.clear();
						CacheManager.resetTerms();
					
				}else{
					logMessage (false, IEvent.TERM_CREATE);
					}}
							else{logMessage (false, IEvent.TERM_CREATE);
					}}
			else 
			logMessage (false, IEvent.EMPTY_CREATE);
				return "success";
	}
	
	
	private boolean dateValidate(List<Object> l, String t1, String t2)throws ParseException{
		DateFormat f=new SimpleDateFormat("yyyy-mm-dd");
		Date startDate=f.parse(t1);
		Date endDate=f.parse(t2);
		
		for(Object o: l){
			Term term=(Term)o;
			Date sd=f.parse(term.getStartDate());
			Date ed=f.parse(term.getEndDate());
			if((startDate.after(sd)&&startDate.before(ed)
					||(endDate).after(sd)&&endDate.before(ed))
					||(startDate.equals(endDate))){
				return false;
			}
		}
		return true;
	}
			
	public String refresh () {		
		return submit();
	}
	
	@Override
	protected String delete() {
		// TODO Auto-generated method stub
		return null;
	}
	
protected void logMessage (boolean success, int event) {
		Logger lg=new Logger();
		Message messages = (Message) session.getAttribute("messages");
	
		if ((event == IEvent.TERM_CREATE)&&noOverlapping&&noTimeConflict){ 
			message = "Create Term [" + ((TermBean)bean).getName() + "]";
			lg.output("Error",message);
			}
		else if( (event == IEvent.TERM_CREATE)&&!noOverlapping){
			message = "Create Term [" + ((TermBean)bean).getName() + "] Overlapping";
			lg.output("Error",message);
			}
		else if	((event == IEvent.TERM_CREATE)&&noOverlapping&&!noTimeConflict){
			message = "Create Term [" + ((TermBean)bean).getName() + "] TimeConflict";
			lg.output("Error",message);
			}
		else if (event == IEvent.TERM_DELETE){
			//termName display error!
			message = "Delete Term [" + ((TermBean)bean).getName() + "]";
			lg.output("Success",message);	
		}
		else if (event == IEvent.EMPTY_SEARCH){ 
			message = "No search results to display.";
			lg.output("Error",message);	
		}
		else if (event == IEvent.EMPTY_CREATE) {
			message = "Please fill in all fields.";
			lg.output("Error",message);
		}
		else if (event == IEvent.TERM_SET&&!success){
			message = "Set Term [" + ((TermBean)bean).getName() + "] invalid date";
			lg.output("Error",message);	
		}
		else if (event == IEvent.TERM_SET&&success){
			message = "Set Term [" + ((TermBean)bean).getName() + "]";
			lg.output("Success",message);
			}
		messages.setMessage (success,message);
		
	}
	
	}


