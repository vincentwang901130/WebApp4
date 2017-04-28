package edu.scs.carleton.comp.ls.view.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.scs.carleton.comp.ls.view.domain.Course;
import edu.scs.carleton.comp.ls.view.domain.StuCourse;
import edu.scs.carleton.comp.ls.view.domain.Term;

public class DBStuCourse extends DAO{
	public DBStuCourse(){
		super.init();
		this._dao=new DAOHelper();
	}
	
	public DBStuCourse(DAOHelper daoHelper){
		super.init();
		this._dao=daoHelper;
	}
	
	public boolean create(Object o){
		StuCourse stucourse=(StuCourse) o;
		return create(stucourse.getStuNo(),
				stucourse.getCourseCode(),
				stucourse.getRegisterDate(),
				stucourse.getTermid());
	}
	
	public boolean create(String stuNo, String course, String registerDate, int termid){
		String sql=this._sqlCode.getProperty("stucourse.insert");
		String sqlString=MessageFormat.format(sql,new Object[]{
				stuNo,
				course,
				registerDate,
				termid
		});
		return this._dao.executeUpdate(sqlString);
	}
	
	public ArrayList<Object> findAll(){
		String sql=this._sqlCode.getProperty("stucourse.findAll");
		return super.processResultSet(this._dao.executeLookup(sql, "stucourse.findall"));
	}
	
	public boolean update(Object o){
		//.....
		return true;
	}
	
	protected Object getDataObject(ResultSet rs, boolean close){
		StuCourse stucourse = null;
		try{
			if(rs.next()){
				stucourse=new StuCourse(
						rs.getString(1),
						rs.getString(2),
						rs.getString(3),
						rs.getInt(4)
						);		
			}
		}catch(SQLException e){
			edu.scs.carleton.comp.ls.utils.ExceptionHandler.display(this, e);
		}finally{
			if (close) close(rs);
		}
		return stucourse;
	}
	
	public boolean delete(int paraInt){
		return false;
	}
	
	public boolean delete(String courseCode){
		StuCourse stucourse = (StuCourse)findByPrimaryKey(courseCode);
		if(stucourse!=null){
			return delete(stucourse);
		}
		return false;
	}
	
	public boolean delete(String courseCode, String stuNo){
		String sql=this._sqlCode.getProperty("stucourse.deleteMyCourse");
		String sqlString = MessageFormat.format(sql, new Object[] { courseCode, stuNo });
		return this._dao.executeUpdate(sqlString);
	}
	
	public boolean delete(Object o) {
		StuCourse stucourse = (StuCourse)o;
		
		String sql = this._sqlCode.getProperty("stucourse.deleteMyCourse");
		String sqlString = MessageFormat.format(sql, new Object[] { stucourse.getCourseCode() });
		return this._dao.executeUpdate(sqlString);
	}
	
	public ArrayList<Object> findall(){
		String sql=this._sqlCode.getProperty("stucourse.findAll");
		return super.processResultSet(this._dao.executeLookup(sql,"stucourse.findAll"));
	}
	
	public boolean deleteAll() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public Object findByPrimaryKey(int paramInt) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public Object find(Object o){
		return null;
	}
	
	public Object findByPrimaryKey(String primaryKey){
		return null;
	}

	public ArrayList<Object> findcoursecode() {
		String sql=this._sqlCode.getProperty("stucourse.findcoursecode");
		return super.processResultSet(this._dao.executeLookup(sql,"stucourse.findcoursecode"));
	}
}