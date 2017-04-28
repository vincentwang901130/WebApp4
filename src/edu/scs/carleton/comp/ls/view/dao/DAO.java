package edu.scs.carleton.comp.ls.view.dao;
 
 import edu.scs.carleton.comp.ls.utils.*;
 import java.io.File;
 import java.io.FileInputStream;
 import java.io.IOException;
 import java.sql.ResultSet;
 import java.util.ArrayList;
 import java.util.Properties;


 		public abstract class DAO implements IDAO
 		{
 			protected DAOHelper _dao = null;
 			protected Properties _sqlCode = null;
 			public DAO() {}
 			
 			protected abstract Object getDataObject(ResultSet paramResultSet, boolean paramBoolean);
 			
 			protected void init()
 			{
 				try {
 					String sqlFile = ConfigProperties.getInstance()._SQL_DIR;
 					File resFile = new File(sqlFile);
 					FileInputStream fileInput = new FileInputStream(resFile);
 					this._sqlCode = new Properties();
 					this._sqlCode.load(fileInput);
 					fileInput.close();
 				} catch (IOException e) {
 					e.printStackTrace();
 				}
 			}
  
 			public void destroy()
 			{
 				if (this._dao != null)
 				{
 					this._dao.returnConnection();
 					this._dao = null;
 					this._sqlCode = null;
 				}
 				try
 				{
 					super.finalize();
 				} catch (Throwable t) {
 					t.printStackTrace();
 				}
 			}
 			protected ArrayList<Object> processResultSet(ResultSet rs)
 			{
 				ArrayList<Object> list = new ArrayList();
 				Object obj;
 				while ((obj = getDataObject(rs, false)) != null) {
 				list.add(obj);
 				}
 				this._dao.close(rs);
 				return list;
 			}
 
 			protected void close(ResultSet rs) {
 				this._dao.close(rs);
 			}
 }



