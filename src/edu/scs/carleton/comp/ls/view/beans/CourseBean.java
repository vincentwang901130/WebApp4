package edu.scs.carleton.comp.ls.view.beans;

public class CourseBean extends Bean{
  
	String courseCode;
	String courseName;
	int termid;
	String meetingTimes;
	String time;
	String location;

	
	public final String getCourseCode() {
		return courseCode;
	}
	public final void setCourseCode(String string) {
		this.courseCode = string;
	}




	public final String getCourseName() {
		return courseName;
	}




	public final void setCourseName(String courseName) {
		this.courseName = courseName;
	}


	public final int getTermid() {
		return termid;
	}

	public final void setTermid(int termid) {
		this.termid = termid;
	}




	public final String getMeetingTimes() {
		return meetingTimes;
	}




	public final void setMeetingTimes(String meetingTimes) {
		this.meetingTimes = meetingTimes;
	}




	public final String getTime() {
		return time;
	}

	public final void setTime(String time) {
		this.time = time;
	}

	public final String getLocation() {
		return location;
	}




	public final void setLocation(String location) {
		this.location = location;
	}




	



	
	
	

	public void clear() {
		this.courseName = null;
	
		this.courseCode = null;
	    this.time=null;
		this.location = null;
		this.meetingTimes = null;
		
	}

}
