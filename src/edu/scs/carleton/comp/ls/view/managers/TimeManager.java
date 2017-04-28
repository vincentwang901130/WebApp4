package edu.scs.carleton.comp.ls.view.managers;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.ejb.Singleton;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import edu.scs.carleton.comp.ls.view.beans.TimeBean;
@Singleton
@ManagedBean
@RequestScoped
public class TimeManager {
	public String Time;
	private String time;
	private String currenttime;
	public TimeManager(){}
	public final String getTime() {
		return time;
	}
	public final void setTime(String time) {
		this.time = time;
	}
	public final String getCurrenttime() {
		return currenttime;
	}
	public final void setCurrenttime(String currenttime) {
		this.currenttime = currenttime;
	}

	public void changetime(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
		String today = sdf.format(Calendar.getInstance().getTime());
		if(currenttime.equals("")){
			time=today;
		Time=time;
		}
		else 
			time =currenttime;
		Time=time;
	}

}
