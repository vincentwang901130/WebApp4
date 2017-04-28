package edu.scs.carleton.comp.ls.view.domain;
import java.io.Serializable;
public class Course implements Serializable{
	 private static final long serialVersionUID = 1L;
	 private String courseCode;
	 private Integer termid;
	 private String courseName;
	 private String meetingTimes;
	 private String time;
	 private String location;
	
	 public final Integer getTermid() {
			return termid;
		}

		public final void setTermid(Integer termid) {
			this.termid = termid;
		}

	
	 public Course(String courseCode,String courseName,Integer termid, String meetingTimes, String time, String location)
	 {
		 this.courseCode=courseCode;
		 this.termid=termid;
		 this.courseName=courseName;
		 this.meetingTimes=meetingTimes;
		 this.time=time;
		 this.location=location;
		 
	 }
	 
	 public Course(String courseCode, String courseName, String meetingTimes, String time, String location){
		this.courseCode=courseCode;
		this.courseName=courseName;
		this.meetingTimes=meetingTimes;
		this.time=time;
		this.location=location;
	 }
	 
	
	
	 	public final String getCourseCode() {
			return courseCode;
		}

		public final void setCourseCode(String courseCode) {
			this.courseCode = courseCode;
		}

		

		public final String getCourseName() {
			return courseName;
		}

		public final void setCourseName(String courseName) {
			this.courseName = courseName;
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

	

		public static final long getSerialversionuid() {
			return serialVersionUID;
		}

	 
}
