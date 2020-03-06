package com.java.testool;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.ComparisonFailure;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.java.beans.Process;
import com.java.beans.Task;
import com.java.enums.ControlTypes;
import com.java.enums.ElementTypes;
import com.java.enums.ResultTypes;


public class MyMain {

	public static void main(String[] args) {
		
		// these values will come from UI
	//--------------Input 1 ------------------//
		
		String baseUrl = "http://128.1.98.9:8083/Backoffice/common/login.jsp";
		List<Task> taskList = new ArrayList<>();

		Task pn = new Task("login_input1", ElementTypes.CLASS, "Username", false, true, "IT.USER1", ControlTypes.TEXT_BOX);
		Task p = new Task("txtPassword", ElementTypes.ID, "Password", false, true, "XYZxyz123", ControlTypes.TEXT_BOX);
		Task p0 = new Task("bluebtn", ElementTypes.CLASS , "Login Button", true, false, "", ControlTypes.BUTTON);	
		Task p1 = new Task("Life Insurance", ElementTypes.PARTIAL_TEXT, "Life Insurance Button", true, false, "", ControlTypes.BUTTON);
		
		// Need to add in order
		taskList.add(pn); taskList.add(p); taskList.add(p0); taskList.add(p1); 

		Process process = new Process("Login", taskList, ResultTypes.TITLE, "FWU AG");
		executeProcess(process, baseUrl);
		System.out.println(process);
		
		
		
		
	}
	
	
	@Test
	private static void executeProcess(Process process, String baseUrl)
	{
		Testool testool = new Testool(baseUrl);
		String actualResult = "";
		
		for(Task task : process.getTasks())
		{
			WebElement element = testool.getWebElement(task.getElementName(), task.getElementType());
			
			if(task.getControlType().equals(ControlTypes.TEXT_BOX))
			{
				element.clear();// Do something
				element.sendKeys(task.getFieldValue());
			}
			else if(task.getControlType().equals(ControlTypes.BUTTON))
			{
				element.click();
			}
			else if(task.getControlType().equals(ControlTypes.DROP_DOWN))
			{
				Select dropdown = new Select(element);
				dropdown.selectByValue(task.getFieldValue());
			}
			else if(task.getControlType().equals(ControlTypes.CHECK_BOX))
			{
				element.click();
			}
			
			
			
			/*
			if(task.isField())
			{
				element.clear();
				element.sendKeys(task.getFieldValue());
			}
			
			if(task.isClick())
			{
				element.click();
			}
			*/
			
			
		}
		
		actualResult = testool.getResult(process.getResultType());
		
		try
		{
			assertEquals(process.getExpectedResult(), actualResult);
			process.setStatus(true);
			process.setStatusMessage("Process: "+process.getProcessName()+" Executed SuccessFully");
		}
		catch (ComparisonFailure exp)
		{
			process.setStatus(false);
			process.setStatusMessage("Process: "+process.getProcessName()+" Failed: "+exp.getMessage());
		}
		
	}

}
