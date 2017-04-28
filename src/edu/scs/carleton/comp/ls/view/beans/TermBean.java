package edu.scs.carleton.comp.ls.view.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class TermBean extends Bean {
	private Integer termid;
	private String name;
	private String startDate;
	private String endDate;
	private String enrollStart;
	private String enrollEnd;
	private String dropDeadline;
	
	
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
	public final Integer getTermid() {
		return termid;
	}

	public final void setTermid(Integer termid) {
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


	
	public void clear () {
		this.enrollEnd=null;
		this.enrollStart=null;
		this.dropDeadline=null;
		this.name=null;
		this.startDate = null;
		this.endDate = null;
	}
}
