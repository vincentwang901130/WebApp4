package edu.scs.carleton.comp.ls.view.beans;

import javax.ejb.Singleton;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
@Singleton
@ManagedBean
@RequestScoped
public class TimeBean extends Bean{
	String  currenttime;
	
	public final String getCurrenttime() {
		return currenttime;
	}

	public final void setCurrenttime(String currenttime) {
		this.currenttime = currenttime;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		currenttime=null;
	}

}
