package edu.scs.carleton.comp.ls.view.dao;

import edu.scs.carleton.comp.ls.dbam.connection.MySqlConnection;
import edu.scs.carleton.comp.ls.utils.ExceptionHandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
 
	public class DAOHelper
	{
		public int affectrow;
		private MySqlConnection connection = null;
								
		public DAOHelper()
		{
			this.connection = new MySqlConnection();
		}
 
		public boolean execute(String sql)
		{
			return this.connection.executeStatement(sql);
		}
		public boolean executeUpdate(String sql)
		{
			affectrow=this.connection.executeUpdate(sql);
 		 return affectrow != 0;
		}
		
	
		public ResultSet executeLookup(String sql, String originator)
		{
			PreparedStatement ps = null;
			try {
				ps = this.connection.prepareStatement(sql);
				return ps.executeQuery(sql);
			} catch (SQLException e) {
				ExceptionHandler.display(this, originator, e);
			} catch (Exception e) {
				ExceptionHandler.display(this, "executeLookup", e);
			}
			return null;
		}
  
		public boolean executeStoredProcedure(String sql, String originator)
		{
			PreparedStatement ps = null;
			try
			{
				ps = this.connection.prepareStatement(sql);
				return ps.execute();
			} catch (SQLException e) {
				ExceptionHandler.display(this, originator, e);
			} catch (Exception e) {
				ExceptionHandler.display(this, "executeStoredProcedure", e);
			}
     
			return false;
		}
   
		
		
		
		
		
		public int getCount(String sql, String originator)
		{
			try {
				PreparedStatement ps = this.connection.prepareStatement(sql);
				ResultSet rs = ps.executeQuery(sql);				
				rs.next();
				int count = rs.getInt(1);
				rs.close();
  				return count;
			} catch (SQLException e) {
				ExceptionHandler.display(this, originator, e);
			} catch (Exception e) {
				ExceptionHandler.display(this, "getCount", e);
			}
			return -1;
		}
		
			public boolean returnConnection()
			{
				if (this.connection != null) {
					this.connection.returnConnection();
					this.connection = null;
				}
				return true;
			}
  
			protected ResultSet getNextRow(ResultSet rs)
			{
				try {
					if (rs.next())
						return rs;
				} catch (SQLException e) {
					ExceptionHandler.display(this, "getNextRow", e);
					return null;
				}	
				return rs;
			}
   
			protected void close(ResultSet rs) {
				if (rs != null) {
		   try
		   {
			   rs.close();
		   } catch (SQLException e) {
			   ExceptionHandler.display(this, "ResultSet exception", e);
		   }
				}
			}

		
		}

