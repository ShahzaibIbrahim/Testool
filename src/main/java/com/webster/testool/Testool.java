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
		System.setProperty("webdriver.chrome.driver","src/main/resources/drivers/chromedriver.exe"); // Is there a better way?
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

		switch (type) {
			case ID:
				element = driver.findElement(By.id(elementName));
				break;
			case CLASS:
				element = driver.findElement(By.className(elementName));
				break;
			case PARTIAL_TEXT:
				element = driver.findElement(By.partialLinkText(elementName));
				break;
			case LINK_TEXT:
				element = driver.findElement(By.linkText(elementName));
				break;
			case CSS_SELECTOR:
				element = driver.findElement(By.cssSelector(elementName));
				break;
			case NAME:
				element = driver.findElement(By.name(elementName));
				break;
		}
		
		return element;
	}
	
	public String getResult(ResultTypes resultType)
	{
		switch (resultType) {
			case TITLE:
				return driver.getTitle();
			case URL:
				return driver.getCurrentUrl();
			case SOURCE:
				return driver.getPageSource();
		}
		
		return null;
	}

}
