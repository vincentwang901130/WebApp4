package edu.scs.carleton.comp.ls.view.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import edu.scs.carleton.comp.ls.admin.Role;
import edu.scs.carleton.comp.ls.view.utils.Debug;

@ManagedBean
@RequestScoped
public class UserBean extends Bean {

	private int stuId;  
	private String stuNo;
  	private String password;
  	private String firstname;
  	private String lastname;
    private String birthdate;
    private String school;
    private String degree;
    private String timestatus;
	
	

	public final int getStuId() {
		return stuId;
	}



	public final void setStuId(int stuId) {
		this.stuId = stuId;
	}



	public final String getStuNo() {
		return stuNo;
	}



	public final void setStuNo(String stuNo) {
		this.stuNo = stuNo;
	}



	public final String getPassword() {
		return password;
	}



	public final void setPassword(String password) {
		this.password = password;
	}



	public final String getFirstname() {
		return firstname;
	}



	public final void setFirstname(String firstname) {
		this.firstname = firstname;
	}



	public final String getLastname() {
		return lastname;
	}



	public final void setLastname(String lastname) {
		this.lastname = lastname;
	}



	public final String getBirthdate() {
		return birthdate;
	}



	public final void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}



	public final String getSchool() {
		return school;
	}



	public final void setSchool(String school) {
		this.school = school;
	}



	public final String getDegree() {
		return degree;
	}



	public final void setDegree(String degree) {
		this.degree = degree;
	}



	public final String getTimestatus() {
		return timestatus;
	}



	public final void setTimestatus(String timestatus) {
		this.timestatus = timestatus;
	}



	public void clear () {
		
		this.stuNo=null;
		this.firstname=null;
		this.lastname=null;
		this.degree=null;
		this.school=null;
		this.birthdate=null;
		this.timestatus=null;
	}
	
}
