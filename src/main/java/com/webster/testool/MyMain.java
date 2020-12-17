package com.webster.testool;


import java.util.ArrayList;
import java.util.List;

import com.webster.handler.ProcessExecutionHandler;

import com.webster.beans.Process;
import com.webster.beans.Task;
import com.webster.enums.ControlTypes;
import com.webster.enums.ElementTypes;
import com.webster.enums.ResultTypes;


public class MyMain {

	public static void main(String[] args) {
		
		// these values will come from UI
		//--------------Input 1 ------------------//
		
		String baseUrl = "http://128.1.95.113:8080/Backoffice/common/login.jsp";
		List<Task> taskList = new ArrayList<>();

		Task pn = new Task("login_input1", ElementTypes.CLASS, "Username", "ES.USER1", ControlTypes.TEXT_BOX);
		Task p = new Task("txtPassword", ElementTypes.ID, "Password", "XYZxyz1234", ControlTypes.TEXT_BOX);
		Task p0 = new Task("blubtn", ElementTypes.CLASS , "Login Button", "", ControlTypes.BUTTON);
		//Task p1 = new Task("Life Insurance", ElementTypes.PARTIAL_TEXT, "Life Insurance Button", true, false, "", ControlTypes.BUTTON);
		
		// Need to add in order
		taskList.add(pn); taskList.add(p); taskList.add(p0);// taskList.add(p1);

		Process process = new Process("Login", taskList, ResultTypes.TITLE, "FWU AG");

		ProcessExecutionHandler processExecutionHandler = new ProcessExecutionHandler();
		processExecutionHandler.executeProcess(process, baseUrl);

		System.out.println("Printing Process : \n\n"+ process);

	}



}
