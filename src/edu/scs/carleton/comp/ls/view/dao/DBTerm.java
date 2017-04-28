package edu.scs.carleton.comp.ls.view.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.scs.carleton.comp.ls.book.Author;
import edu.scs.carleton.comp.ls.view.domain.Term;
	
public class DBTerm extends DAO{
		public DBTerm()
		{
			super.init();
			this._dao = new DAOHelper();   
		}
		public DBTerm(DAOHelper dAOHelper) {
		super.init();
			this._dao = dAOHelper;
		}
		
		public ArrayList<Object> findall(){
			String sql=this._sqlCode.getProperty("term.findall");
			 return super.processResultSet(this._dao.executeLookup(sql,"term.findall"));
		}
		
		
		public boolean create(String Name, String startDate, String endDate)
					{
						String sql = this._sqlCode.getProperty("term.insert");
						String sqlString = MessageFormat.format(sql, new Object[] { Name, startDate, endDate});
						return this._dao.executeUpdate(sqlString);
					}
		public boolean create(String Name, String startDate, String endDate, String enrollStart, String enrollEnd, String dropDeadline)
		{
			String sql = this._sqlCode.getProperty("term.insert");
			String sqlString = MessageFormat.format(sql, new Object[] { Name, startDate, endDate, enrollStart, enrollEnd, dropDeadline });
			return this._dao.executeUpdate(sqlString);
		}
		
		
				public boolean create(Object o) {
						Term term = (Term)o;
						return create(term.getName(), term.getStartDate(),term.getEndDate(), term.getEnrollStart(), term.getEnrollEnd(), term.getDropDeadline());
			   }
				
				
				
				
				
				
		public Object findByPrimaryKey(String primarykey) {
			 String sql = this._sqlCode.getProperty("term.findByPrimaryKey");
			 String sqlString=MessageFormat.format(sql, new Object[]{Integer.parseInt(primarykey)});
		     return this._dao.execute(sqlString);
		}
		
			protected Object getDataObject(ResultSet rs, boolean close) {
				Term term = null;
				try
				{
					if (rs.next())
			    	term = 
			    	new Term(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
					} catch (SQLException e) {
					edu.scs.carleton.comp.ls.utils.ExceptionHandler.display(this, e);
					} catch (Exception e) {
			      edu.scs.carleton.comp.ls.utils.ExceptionHandler.display(this, e);
					} finally {
			     if (close) { close(rs);
			     }	
			    }
		     return term;
		   }
			public ArrayList<Object> findByName(String name) {
				 String sql = this._sqlCode.getProperty("term.findByName");
				 String sqlString=MessageFormat.format(sql, new Object[]{Integer.parseInt(name)});
				  return super.processResultSet(this._dao.executeLookup(sqlString, "term.findByName"));
			}
			public ArrayList<Object> getListOfTerms() {
				 String sql = this._sqlCode.getProperty("term.getList");
		        return super.processResultSet(this._dao.executeLookup(sql, "term.getListOfTerms"));
			}
			
			public boolean update(String name, String enrollStart, String enrollEnd){
				String sql=this._sqlCode.getProperty("term.updateBytermName");
				String sqlString=MessageFormat.format(sql, new Object[] {name, enrollStart, enrollEnd});
				return this._dao.executeUpdate(sqlString);
			}
			public boolean update(String name, String enrolldeadline){
				String sql=this._sqlCode.getProperty("term.updatedeadline");
				String sqlString=MessageFormat.format(sql, new Object[] {name, enrolldeadline});
				return this._dao.executeUpdate(sqlString);
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
			@Override
			public boolean update(Object paramObject) {
				// TODO Auto-generated method stub
				return false;
			}
		
	
			
			
			}
	
	

