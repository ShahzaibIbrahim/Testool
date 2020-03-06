package com.java.beans;

import java.util.List;

import com.java.enums.ResultTypes;

public class Process {
	
	
	private String processName;
	private List<Task> tasks;
	private ResultTypes resultType;
	private String expectedResult;
	private boolean status;
	private String statusMessage;
	
	/**
	 * 
	 * @param processName
	 * @param tasks
	 * @param resultType
	 * @param expectedResult
	 */
	public Process(String processName, List<Task> tasks, ResultTypes resultType, String expectedResult) {
		super();
		this.processName = processName;
		this.tasks = tasks;
		this.resultType = resultType;
		this.expectedResult = expectedResult;
	}


	public String getProcessName() {
		return processName;
	}


	public void setProcessName(String processName) {
		this.processName = processName;
	}


	public List<Task> getTasks() {
		return tasks;
	}


	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}


	public ResultTypes getResultType() {
		return resultType;
	}


	public void setResultType(ResultTypes resultType) {
		this.resultType = resultType;
	}


	public String getExpectedResult() {
		return expectedResult;
	}


	public void setExpectedResult(String expectedResult) {
		this.expectedResult = expectedResult;
	}


	public boolean isStatus() {
		return status;
	}


	public void setStatus(boolean status) {
		this.status = status;
	}


	public String getStatusMessage() {
		return statusMessage;
	}


	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}


	@Override
	public String toString() {
		return "Process [processName=" + processName + ", tasks=" + tasks + ", resultType=" + resultType
				+ ", expectedResult=" + expectedResult + ", status=" + status + ", statusMessage=" + statusMessage
				+ "]";
	}
	
	
	
	
	
}
