package edu.scs.carleton.comp.ls.view.beans;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import edu.scs.carleton.comp.ls.view.beans.Bean;
import edu.scs.carleton.comp.ls.view.utils.Debug;

@ManagedBean
@RequestScoped
public class StuCourseBean extends Bean{
	private String stuNo;
	private String courseCode;
	private String registerDate;
	private int termid;
	public final String getStuNo() {
		return stuNo;
	}
	public final void setStuNo(String stuNo) {
		this.stuNo = stuNo;
	}
	
	public final String getCourseCode() {
		return courseCode;
	}
	public final void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	public final String getRegisterDate() {
		return registerDate;
	}
	public final void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}
	
	public void clear(){
		this.stuNo=null;
		this.courseCode=null;
		this.registerDate=null;
	}
	
	public final int getTermid() {
		return termid;
	}
	public final void setTermid(int termid) {
		this.termid = termid;
	}
}