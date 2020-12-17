 package com.webster.testool;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.webster.enums.ElementTypes;
import com.webster.enums.ResultTypes;


public class Testool {
    
	private String baseUrl;	// may not be needed here
	private WebDriver driver;
	
	
	public Testool(String baseUrl)
	{
		this.baseUrl = baseUrl;
		System.setProperty("webdriver.chrome.driver","src/main/resources/drivers/chromedriver.exe");
	    driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        driver.get(this.baseUrl);
	}
	
	
	public WebDriver getDriver() {
		return driver;
	}



	public WebElement getWebElement(String elementName, ElementTypes type)
	{
		WebElement element = null;
		if(ElementTypes.ID.equals(type))
		{
			element = driver.findElement(By.id(elementName));
		}
		else if(ElementTypes.CLASS.equals(type))
		{
			element = driver.findElement(By.className(elementName));
		}
		else if(ElementTypes.PARTIAL_TEXT.equals(type))
		{
			element = driver.findElement(By.partialLinkText(elementName));
		}
		else if(ElementTypes.LINK_TEXT.equals(type))
		{
			element = driver.findElement(By.linkText(elementName));
		}
		else if(ElementTypes.CSS_SELECTOR.equals(type))
		{
			element = driver.findElement(By.cssSelector(elementName));
		}
		else if(ElementTypes.NAME.equals(type))
		{
			element = driver.findElement(By.name(elementName));
		}
		
		
		return element;
	}
	
	public String getResult(ResultTypes resultType)
	{
		if(ResultTypes.TITLE.equals(resultType))
		{
			return driver.getTitle();
		}
		else if(ResultTypes.URL.equals(resultType))
		{
			return driver.getCurrentUrl();
		}
		else if(ResultTypes.SOURCE.equals(resultType))
		{
			return driver.getPageSource();
		}
		
		return null;
	}
	
	/*
	public WebElement getCheckedElement(String elementName)
	{
		WebElement element = driver.get(arg0);
	}*/
}
