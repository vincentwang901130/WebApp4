package edu.scs.carleton.comp.ls.view.beans;

import javax.ejb.Singleton;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import edu.scs.carleton.comp.ls.view.utils.IImage;
import edu.scs.carleton.comp.ls.view.utils.IPane;
import edu.scs.carleton.comp.ls.view.utils.Message;

@Singleton
@ManagedBean
@SessionScoped
public class BackingBean implements IImage {

	private String action;
	private String resultsPane;
	private String image;
	private String contextRoot;
	private BakedBean oven;
	
	
	public BackingBean () {
		this.action = IPane.BLANK;
		this.image = IImage.ADMIN_DEFAULT;
		this.resultsPane = IPane.BLANK;
		this.oven = new BakedBean();
	}
	
	
	
	public String changeApplication () {
		return "changeApplication";
	}
	
	public void setAction(ActionEvent e) {
		String actionEvent = e.getComponent().getId();
				
		this.clearMessages();
		
		 if ( actionEvent.equalsIgnoreCase("maintainTerm") ) {
				this.action = IPane.Term_PANE;
				this.image = IImage.ADMIN_DEFAULT;
				this.resultsPane = IPane.Term_RESULTS;
			}
			else
			if ( actionEvent.equalsIgnoreCase("maintainCourse") ) {
				this.image = IImage.ADMIN_DEFAULT;
				this.action = IPane.Course_PANE;
				this.resultsPane = IPane.Course_RESULTS;
			}	
		 	
			else
			if ( actionEvent.equalsIgnoreCase("maintainAssignment") ) {
				this.action = IPane.Assignment_PANE;
				this.image = IImage.ADMIN_DEFAULT;
				this.resultsPane = IPane.Assignment_RESULTS;
			}
			else
				if ( actionEvent.equalsIgnoreCase("takecourse") ) {
				this.image = IImage.ADMIN_DEFAULT;
				this.action = IPane.TakeCOURSE_PANE;
				this.resultsPane = IPane.TakeCourse_RESULTS;
			}
			else
				if ( actionEvent.equalsIgnoreCase("dropCourse") ) {
				this.image = IImage.ADMIN_DEFAULT;
				this.action = IPane.DropCourse_PANE;
				//this.resultsPane = IPane.DropCourse_RESULTS;
			}
				else
					if ( actionEvent.equalsIgnoreCase("assignmentExam") ) {
					this.image = IImage.ADMIN_DEFAULT;
					this.action = IPane.AssignmentExam_PANE;
					this.resultsPane = IPane.AssignmentExam_RESULTS;
				}
					else if(actionEvent.equalsIgnoreCase("changetime")){
						this.action = IPane.TIMESETPANE;
						//this.image = IImage.ADMIN_DEFAULT;
						//this.resultsPane = IPane.BLANK;
					}
			else {
				this.action = IPane.BLANK;
				this.image = IImage.ADMIN_DEFAULT;
				this.resultsPane = IPane.BLANK;
			}
		 	
			
		/*
	   if ( actionEvent.equalsIgnoreCase("maintainMembers") ) {
			this.action = IPane.USER_PANE;
			this.image = IImage.ADMIN_DEFAULT;
			this.resultsPane = IPane.USER_RESULTS;
		}
		else
		if ( actionEvent.equalsIgnoreCase("maintainTitles") ) {
			this.image = IImage.ADMIN_TITLES;
			this.action = IPane.TITLES_PANE;
			this.resultsPane = IPane.TITLES_RESULTS;
		}
		else
		if ( actionEvent.equalsIgnoreCase("maintainAuthors") ) {
			this.action = IPane.AUTHORS_PANE;
			this.image = IImage.ADMIN_AUTHORS;
			this.resultsPane = IPane.AUTHORS_RESULTS;
		}
		else
		if ( actionEvent.equalsIgnoreCase("maintainPublishers") ) {
			this.action = IPane.PUBLISHERS_PANE;
			this.image = IImage.ADMIN_PUBLISHERS;
			this.resultsPane = IPane.PUBLISHERS_RESULTS;
		}
		else
		if ( actionEvent.equalsIgnoreCase("monitoringSystem") ) {
			this.action = IPane.EVENT_PANE;
			this.image = IImage.ADMIN_DEFAULT;
			this.resultsPane = IPane.BLANK;
		}
		
		else
		if ( actionEvent.equalsIgnoreCase("maintainItems") ) {
			this.action = IPane.ITEMS_PANE;
			this.image = IImage.ADMIN_ITEMS;
			this.resultsPane = IPane.ITEMS_RESULTS;
		}
		else {
			this.action = IPane.BLANK;
			this.image = IImage.ADMIN_DEFAULT;
			this.resultsPane = IPane.BLANK;
		}
		*/
		
	oven.bakeBeans();
	}
		
	
	
	public final String getAction () {
		return this.action;
	}
	
	public final String getResultsPane () {
		return resultsPane;	
	}
	
	public String getWelcomeImage () {
		return IImage.WELCOME;
	}
	
	public final String getImage () {
		return this.image;
	}

	public String getContextRoot() {
		return contextRoot;
	}

	public String logOut () {
		clearMessages ();
		this.action = IPane.BLANK;
		this.image = IImage.ADMIN_DEFAULT;
		this.resultsPane = IPane.BLANK;
		return "loggedOut";
	}
	
	private void clearMessages () {
		try {
			FacesContext facesContext = FacesContext.getCurrentInstance();  		
			HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
			Message messages = (Message) session.getAttribute("messages");
			messages.clear();
		} catch ( Exception e) {
			//Debug.trace(this,"clearErrorMessagers",e.getLocalizedMessage());
		}
	}

	
}
