package edu.scs.carleton.comp.ls.view.dao;
import edu.scs.carleton.comp.ls.view.domain.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DBUser extends DAO{

	 public DBUser()
	   {
	    super.init();
	     this._dao = new DAOHelper();
       }
	 
	 public DBUser(DAOHelper daoHelper) {
		     super.init();
		     this._dao = daoHelper;
	  }
	 
	 
	 public List<Object> findall() {
			String sql=this._sqlCode.getProperty("stu.findall");
			 return super.processResultSet(this._dao.executeLookup(sql,"stu.findall"));
		}
		
	 public boolean create(String stuNo, String password,String firstname, String lastname, String birthdate, String school, String degree, String timestatus)
	   {
	    String sql = this._sqlCode.getProperty("stu.insert");
	    boolean flag;
	    String sqlString = MessageFormat.format(sql, new Object[] { 
	    		stuNo,
	    		password,
	    		firstname,
	    		lastname,
	    		birthdate,
	    		school,
	    		degree,
	    		timestatus});
	    flag=this._dao.executeUpdate(sqlString);
	    return flag;
	    	//return flag;
	    }
	 public boolean create(Object o)
	   {
		 			User user = (User)o;
		 			return create(
		 					user.getStuNo(),
		 					user.getPassword(),
		 					user.getFirstname(),
		 					user.getLastname(), 
		 					user.getBirthdate(),
		 			        user.getSchool(),
		 			        user.getDegree(),
		 			        user.getTimestatus());
		   }

	 public Object findByPrimaryKey(String paramString) {
			String sql=this._sqlCode.getProperty("stu.findBystuNo");
			String sqlString=MessageFormat.format(sql, new Object[]{paramString});
			return getDataObject(this._dao.executeLookup(sqlString, "users.findByPrimaryKey"),true);
		}
	 
	 
	 public boolean update(Object o){
		 User user=(User)o;
		 if(findByPrimaryKey(user.getStuNo())==null)
			 return false;
		 String sql=this._sqlCode.getProperty("stu.update");
		 String sqlString=MessageFormat.format(sql, new Object[]{
			 user.getStuNo(),
			 user.getPassword()
		 });
		
		 return this._dao.executeUpdate(sqlString);
	 }
		 
		 
		 
	/* public Object findBystuNo(String stuNo)
	   {
		 String sql = this._sqlCode.getProperty("stu.findBystuNo");
		 String sqlString = MessageFormat.format(sql, new Object[] { stuNo });
		 return getDataObject(this._dao.executeLookup(sqlString, "users.findBystuNo"), true);
	   }*/

	 protected Object getDataObject(ResultSet rs, boolean close) {
		   
	     User user = null;
	     		try {
	     			if (rs.next())
	     			{
	     				user = new User(
	     						       rs.getInt(1),
	     							   rs.getString(2), 
	     							   rs.getString(3), 
	     							   rs.getString(4), 
	     							   rs.getString(5), 
	     							   rs.getString(6),
	     						       rs.getString(7),
	     						       rs.getString(8),
	     						       rs.getString(9)
	     						);			     						
	     				} 
	     		}catch (SQLException e) {
	     				edu.scs.carleton.comp.ls.utils.ExceptionHandler.display(this, e);
	     			}
	     		finally {
	     			if (close) { 
	     				close(rs);
	     			}
	     			}
	     		
	     		return user;

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
	public Object findByPrimaryKey(int paramInt) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object find(Object paramObject) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	}
	
	
	


	
		
			     