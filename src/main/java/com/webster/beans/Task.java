package com.webster.beans;

import com.webster.enums.ControlTypes;
import com.webster.enums.ElementTypes;

public class Task {
	
	private String elementName;
	private ElementTypes elementType;
	private String displayName;
	private boolean click;
	private boolean field;
	private String fieldValue;
	private ControlTypes controlType;
	
	
	/**
	 * 
	 * @param elementName
	 * @param types
	 * @param displayName
	 */
	public Task(String elementName, ElementTypes types, String displayName, boolean click, boolean field, String fieldValue, ControlTypes controlType) {
		super();
		this.elementName = elementName;
		this.elementType = types;
		this.displayName = displayName;
		this.click = click;
		this.field = field;
		this.fieldValue = fieldValue;
		this.controlType = controlType;
		
	}


	public String getElementName() {
		return elementName;
	}


	public void setElementName(String elementName) {
		this.elementName = elementName;
	}


	public ElementTypes getElementType() {
		return elementType;
	}


	public void setElementType(ElementTypes elementType) {
		this.elementType = elementType;
	}


	public String getDisplayName() {
		return displayName;
	}


	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	

	public boolean isClick() {
		return click;
	}


	public void setClick(boolean click) {
		this.click = click;
	}

	
	public boolean isField() {
		return field;
	}


	public void setField(boolean field) {
		this.field = field;
	}


	public String getFieldValue() {
		return fieldValue;
	}


	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}

	

	public ControlTypes getControlType() {
		return controlType;
	}


	public void setControlType(ControlTypes controlType) {
		this.controlType = controlType;
	}


	@Override
	public String toString() {
		return "Task [elementName=" + elementName + ", elementType=" + elementType + ", displayName=" + displayName
				+ ", click=" + click + ", field=" + field + ", fieldValue=" + fieldValue + "]";
	}

	
	
	
	
}
