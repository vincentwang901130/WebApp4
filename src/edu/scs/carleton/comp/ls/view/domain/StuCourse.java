package edu.scs.carleton.comp.ls.view.domain;

import java.io.Serializable;

public class StuCourse implements Serializable{
private String stuNo;
private String courseCode;
private String registerDate;
private int termid;

public final int getTermid() {
	return termid;
}

public final void setTermid(int termid) {
	this.termid = termid;
}

public StuCourse(String stuNo, String courseCode, String registerDate, int termid){
	this.stuNo=stuNo;
	this.courseCode=courseCode;
	this.registerDate=registerDate;
	this.termid=termid;
}

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


}
