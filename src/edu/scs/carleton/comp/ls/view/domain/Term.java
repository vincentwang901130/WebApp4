package edu.scs.carleton.comp.ls.view.domain;
import java.io.Serializable;
public class Term implements Serializable{
	 //private static final long serialVersionUID = 1L;
	
	private String name;
	 private String startDate;
	 private String endDate;
	 private String enrollStart;
	 private String enrollEnd;
	 private String dropDeadline;
	 	
	 
	 
	 public Term(String Name,String startDate, String endDate,String enrollStart, String enrollEnd, String dropDeadline)
	 		{
		 this.name=Name;
		 this.startDate=startDate;
		 this.endDate=endDate;
		 this.enrollStart=enrollStart;
		 this.enrollEnd=enrollEnd;
		 this.dropDeadline=dropDeadline;
	 		}
	 
	 	
	 	public Term(int termid, String Name, String startDate, String endDate, String enrollStart, String enrollEnd, String dropDeadline) {
		 this.termid= termid;
		 this.name = Name;
		 this.startDate = startDate;
		 this.endDate=endDate;
		 this.enrollStart=enrollStart;
		 this.enrollEnd=enrollEnd;
		 this.dropDeadline=dropDeadline;
		}
	 
	 
	 private int termid;
	 public final int getTermid() {
		return termid;
	}
	public final void setTermid(int termid) {
		this.termid = termid;
	}
	public final String getName() {
		return name;
	}
	public final void setName(String name) {
		this.name = name;
	}
	public final String getStartDate() {
		return startDate;
	}
	public final void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public final String getEndDate() {
		return endDate;
	}
	public final void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public final String getEnrollStart() {
		return enrollStart;
	}
	public final void setEnrollStart(String enrollStart) {
		this.enrollStart = enrollStart;
	}
	public final String getEnrollEnd() {
		return enrollEnd;
	}
	public final void setEnrollEnd(String enrollEnd) {
		this.enrollEnd = enrollEnd;
	}
	public final String getDropDeadline() {
		return dropDeadline;
	}
	public final void setDropDeadline(String dropDeadline) {
		this.dropDeadline = dropDeadline;
	}



		/*public static final long getSerialversionuid() {
			return serialVersionUID;
		}*/


}
