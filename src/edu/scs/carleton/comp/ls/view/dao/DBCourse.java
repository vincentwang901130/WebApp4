package edu.scs.carleton.comp.ls.view.dao;
import edu.scs.carleton.comp.ls.view.domain.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
public class DBCourse extends DAO
  
    {
    	public DBCourse(){
    		super.init();
    		this._dao=new DAOHelper();
    	}
    	 public DBCourse(DAOHelper dAOHelper) {
    		    super.init();
    		    this._dao = dAOHelper;
    		  }
    
    	 
    	 public boolean create(Object o)
     {
    	Course course=(Course)o;
    	
        return create(
        		course.getCourseCode(),
        		course.getCourseName(),
        		course.getTermid(),
        		course.getMeetingTimes(),
        		course.getTime(),
        		course.getLocation()
        		);
    
    }
	
     public boolean create(String courseCode, String courseName, int termid, String meetingTimes,
 		String time, String location) {
 		String sql=this._sqlCode.getProperty("course.insert");
 		String sqlString= MessageFormat.format(sql, new Object[]{ 
        courseCode, courseName, termid, meetingTimes, time, location});
 		return this._dao.executeUpdate(sqlString);
 		
 	}
     
	

	protected Object getDataObject(ResultSet rs, boolean close) {
		   
	    Course course = null;
	     		try {
	     			if (rs.next())
	     			{
	     				//System.out.println("course create() starts");
	     				course = new Course(
	     							   rs.getString(1),
	     						       rs.getString(2), 
	     							   rs.getInt(3), 
	     							   rs.getString(4), 
	     							   rs.getString(5),			     						
	     			                   rs.getString(6));
	     				//System.out.println("course create() ends");
	     			}
	     		}catch (SQLException e) {
	     			System.out.println("rs is null,catch by SQLEXception");	
	     			edu.scs.carleton.comp.ls.utils.ExceptionHandler.display(this, e);
	     			}
	     		finally {
	     			if (close) { 
	     				close(rs);
	     				System.out.println("rs is null, close=0");	
	     			}
	     			}
	     		
	     		return course;

	}
	
	public ArrayList<Object> findall(){
		String sql=this._sqlCode.getProperty("course.findall");
		return super.processResultSet(this._dao.executeLookup(sql,"course.findall"));
	}
	
	public Object find(Object o){
		return null;
	}
	
	public List<Object> findByPrimaryKey(int primaryKey) {

 			String sql = this._sqlCode.getProperty("course.findBytermid");
 			String sqlString = MessageFormat.format(sql, new Object[] { primaryKey });
 			return  super.processResultSet(this._dao.executeLookup(sqlString, "course.findBytermid"));
			}
	@Override
	public boolean update(Object paramObject) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean delete(int paramInt) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean delete(String paramString) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean delete(Object paramObject) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean deleteAll() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public Object findByPrimaryKey(String paramString) {
		// TODO Auto-generated method stub
		return null;
	}
	public List<Object> getListOfCourses() {
		 String sql = this._sqlCode.getProperty("course.getList");
	        return super.processResultSet(this._dao.executeLookup(sql, "course.getList"));
	}

	}
    	

	
    
    

    
    


