package edu.scs.carleton.comp.ls.view.managers;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;






//import edu.scs.carleton.comp.ls.book.Author;
//import edu.scs.carleton.comp.ls.book.Publisher;
//import edu.scs.carleton.comp.ls.dbam.DBAuthor;
//import edu.scs.carleton.comp.ls.dbam.DBPublisher;
import edu.scs.carleton.comp.ls.utils.ConfigProperties;
import edu.scs.carleton.comp.ls.view.dao.DBCourse;
import edu.scs.carleton.comp.ls.view.dao.DBTerm;
import edu.scs.carleton.comp.ls.view.domain.Course;
import edu.scs.carleton.comp.ls.view.domain.Term;
import edu.scs.carleton.comp.ls.view.utils.SelectItem;
import edu.scs.carleton.comp.ls.view.utils.Utils;

@Singleton
@ManagedBean
@ApplicationScoped
public class CacheManager {
	
	private static final String ROOT_DIR = Utils.getContextRoot();
	private static final String WEB_INF = "WEB-INF/classes";
	
	//private static SelectItem[] authors = null;
	//private static SelectItem[] publishers = null;

	private static SelectItem[] terms=null;
	private static SelectItem[] courses=null;
	private static SelectItem[] type=null;

	
	public CacheManager () { 
		if (terms == null)
			init(); 
		if(courses==null)
			init();
	    if(type==null)
	    	init();
	
		
	
	}
	
	
	private static void setTypeList(){
		String[] arry={"assignment","midterm","project","final"};
		type = new SelectItem[4];
		for(int i=0;i<4;i++){
			type[i] = new SelectItem (i, arry[i]);
		}
	}
	 public SelectItem[] getListOfType(){
	    	return type;
	    }
	private void init () 
	{	
		String _rootDir = ROOT_DIR + WEB_INF;
		
		ConfigProperties.getInstance(_rootDir);
		
/*		setAuthorsList () ;
		setPublishersList ();*/
		
		setTermsList();
		setCoursesList();
		setTypeList();
		
	}
	
	

    
	private static void setCoursesList() {
		if (courses == null) {
			DBCourse dbcourse = new DBCourse();						
			List<Object> c = dbcourse.getListOfCourses();
			courses = new SelectItem[c.size()];
			dbcourse.destroy();
			
			for (int i=0; i<c.size();i++)
			{
				Course course = (Course)c.get(i);
				//int id=term.getTermid();
				String name = course.getCourseName();
				courses[i] = new SelectItem (course.getCourseCode(), name);
				//System.out.println(id+name);
			}
		}
		
	}

	/*private static void setAuthorsList () {
		if (authors == null) {
			DBAuthor dbAuthor = new DBAuthor();			
			List<Object> a = dbAuthor.getListOfAuthors(); 
			authors = new SelectItem[a.size()];			
			dbAuthor.destroy();
			
			for (int i=0; i<a.size();i++) 
			{
				Author author = (Author)a.get(i);
				String name = author.getFamilyName() + ", " + author.getGivenName();
				authors[i] = new SelectItem (author.getAuthorID(), name);
			}
		}
	}*/

	   
	
	
	
	private static void setTermsList () {
		if (terms == null) {
			DBTerm dbTerm = new DBTerm();						
			List<Object> t = dbTerm.getListOfTerms();
			terms = new SelectItem[t.size()];
			dbTerm.destroy();
			
			for (int i=0; i<t.size();i++)
			{
				Term term = (Term)t.get(i);
				//int id=term.getTermid();
				String name = term.getName();
				terms[i] = new SelectItem (term.getTermid(), name);
				//System.out.println(id+name);
			}
		}
	}
	
	/*private static void setPublishersList () {
		if (publishers == null) {
			DBPublisher dbPublisher = new DBPublisher();						
			List<Object> p = dbPublisher.getListOfPublishers();
			publishers = new SelectItem[p.size()];
			dbPublisher.destroy();
			
			for (int i=0; i<p.size();i++)
			{
				Publisher publisher = (Publisher) p.get(i);
				publishers[i] = new SelectItem (publisher.getPublisherID(), publisher.getName() );
			}
		}
	}
	
	public SelectItem[] getListOfAuthors () {
		return authors;
	}
	
	public SelectItem[] getListOfPublishers () {
		return publishers;
	}*/
    public SelectItem[] getListOfTerms(){
    	return terms;
    }
	/*public static void resetAuthors () {
		clearAuthors ();	
		setAuthorsList () ;
	}
	
	public static void resetPublishers () {
		clearPublishers ();
		setPublishersList ();
	}*/
    public SelectItem[] getListOfCourses(){
    	return courses;
    }
   
    public static void resetTerms(){
		clearTerms();
		setTermsList();
		
    }
	
	public static void resetCourses(){
		clearCourses();
		setCoursesList();
	}
	/*private static void clearAuthors () {
		for (int i = 0; i> authors.length; i++) {
			authors[i] = null;
		}		
		authors = null;
	}
	
	private static void clearPublishers () {
		for (int i = 0; i> publishers.length; i++) {
			publishers[i] = null;
		}		
		publishers = null;
	}*/
	
	

	
	private static void clearCourses() {
		for (int i=0;i>courses.length;i++){
			courses[i]=null;
		}
		courses=null;
	}
		
	

	private static void clearTerms(){
		for (int i=0;i>terms.length;i++){
			terms[i]=null;
		}
		terms=null;
	}
	@PreDestroy
	public void destroy() {		
		//authors = null;
		//publishers = null;
		terms=null;
		courses=null;
	}
	
	@Override
	public void finalize () throws Throwable {
		//clearAuthors();
		//clearPublishers();
        clearTerms();
        clearCourses();
		//authors = null;
		//publishers = null;
		terms=null;
		courses=null;
	}
}
