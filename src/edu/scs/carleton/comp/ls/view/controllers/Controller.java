package edu.scs.carleton.comp.ls.view.controllers;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import edu.scs.carleton.comp.ls.view.dao.*;
import edu.scs.carleton.comp.ls.view.beans.Bean;


public abstract class Controller {

	protected HashMap<Object, Boolean> selectedObjects;
	protected List<Bean> listOfObjects;
	
	protected HashMap<Object, Boolean> selectedObjectsA;
	public HashMap<Object, Boolean> getSelectedObjectsA() {
		return selectedObjectsA;
	}

	public void setSelectedObjectsA(HashMap<Object, Boolean> selectedObjectsA) {
		this.selectedObjectsA = selectedObjectsA;
	}

	public List<Bean> getListOfObjectsA() {
		return listOfObjectsA;
	}

	public void setListOfObjectsA(List<Bean> listOfObjectsA) {
		this.listOfObjectsA = listOfObjectsA;
	}

	public Bean getBeanA() {
		return beanA;
	}

	public void setBeanA(Bean beanA) {
		this.beanA = beanA;
	}

	public DAO getDaoA() {
		return daoA;
	}

	public void setDaoA(DAO daoA) {
		this.daoA = daoA;
	}

	protected List<Bean> listOfObjectsA;
	
	

	protected Bean bean;
	protected Bean beanA;
	protected DAO dao;
	protected DAO daoA;

	protected HttpSession session;
	
	protected String message = "";
	protected boolean render;
	
	public final List<Bean> getListOfObjects() {
		return listOfObjects;
	}

	public final void setListOfObjects(List<Bean> listOfObjects) {
		this.listOfObjects = listOfObjects;
	}

	public Map<Object, Boolean> getSelectedObjects() {
        return selectedObjects;
    }
	
	public final String getMessage() {
		return message;
	}
	
	public boolean isRender() {
		return render;
	}

	public void setRender(boolean render) {
		this.render = render;
	}
	
	public String refresh () {
		return submit();
	}
	
	public String closePane (ActionEvent e) {	
		bean.clear();
		this.render = false;
		listOfObjects.clear();
		selectedObjects.clear();
		return "closed";
	}
	
	public void reset() {
		if (bean != null)
			bean.clear();
		
		selectedObjects.clear();
		listOfObjects.clear();
		render = false;
		message = "";
	}
	
	public void clear () {	
		bean.clear();
		listOfObjects.clear();
		selectedObjects.clear();
		render = false;
	}
	
	protected abstract Bean getBean ();
		
	protected abstract void setBean (Bean bean);
	
	protected abstract String submit();
	
	protected abstract String create () throws ParseException;
	
	protected abstract String delete ();
	
	protected abstract void logMessage (boolean success, int event);
	
	
	
}
