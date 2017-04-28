package edu.scs.carleton.comp.ls.view.domain;

import edu.scs.carleton.comp.ls.admin.Role;

//import edu.scs.carleton.comp.ls.admin.Role;

public class User {
	
	private int stuId;  
	private String stuNo;
  	private String password;
  	private String firstname;
  	private String lastname;
    private String birthdate;
    private String school;
    private String degree;
    private String timestatus;
	//private String middleinitial;
    
   
	public User(String stuNo, String password, String firstname, String lastname, String birthdate, String school)
    {
    	this.stuNo = stuNo;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.school = school;
    	
    	 }
    public User(int stuID, String stuNo, String password, String firstname, String lastname, String birthdate, String school, String degree, String timestatus)
    {
    	this.stuId=stuID;
    	this.stuNo = stuNo;
    	this.password = password;
    	this.firstname = firstname;
    	this.lastname = lastname;
    	//this.middleinitial= middleinitial;
    	this.birthdate = birthdate;
    	this.school = school;
    	this.degree = degree;
    	this.timestatus=timestatus;
    }
	

	public int getStuId() {
		return stuId;
	}

	public void setStuId(int stuId) {
		this.stuId = stuId;
	}

	public String getStuNo() {
		return stuNo;
	}

	public void setStuNo(String stuNo) {
		this.stuNo = stuNo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getTimestatus() {
		return timestatus;
	}

	public void setTimestatus(String timestatus) {
		this.timestatus = timestatus;
	}

/*	 public String getMiddleinitial() {
			return middleinitial;
		}
		public void setMiddleinitial(String middleinitial) {
			this.middleinitial = middleinitial;
		}*/
    
   

	
    }