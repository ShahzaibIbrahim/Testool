package com.webster.beans;

import com.webster.enums.ControlTypes;
import com.webster.enums.ElementTypes;

public class Task {
	
	private String elementName;
	private ElementTypes elementType;
	private String displayName;
	private String fieldValue;
	private ControlTypes controlType;
	private String statusMessage;

	
	
	/**
	 * 
	 * @param elementName
	 * @param types
	 * @param displayName
	 */
	public Task(String elementName, ElementTypes types, String displayName, String fieldValue, ControlTypes controlType) {
		super();
		this.elementName = elementName;
		this.elementType = types;
		this.displayName = displayName;
		this.fieldValue = fieldValue;
		this.controlType = controlType;
		this.statusMessage = "Task Pending";
		
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
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
		return "Task{" +
				"elementName='" + elementName + '\'' +
				", elementType=" + elementType +
				", displayName='" + displayName + '\'' +
				", fieldValue='" + fieldValue + '\'' +
				", controlType=" + controlType +
				", statusMessage='" + statusMessage + '\'' +
				'}';
	}
}
