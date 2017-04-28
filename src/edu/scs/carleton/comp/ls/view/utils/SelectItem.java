package edu.scs.carleton.comp.ls.view.utils;

public class SelectItem {

	private Integer key;
	private String value;
	private String stringkey;
	
	public SelectItem (Integer key, String value) {
		this.key = key;
		this.value = value;
	}

	public SelectItem(String stringkey, String value) {
		// TODO Auto-generated constructor stub
	this.stringkey=stringkey;
	this.value=value;
	}

	public final String getStringkey() {
		return stringkey;
	}

	public final void setStringkey(String stringkey) {
		this.stringkey = stringkey;
	}

	public final Integer getKey() {
		return key;
	}

	public final String getValue() {
		return value;
	}
	
	
}
