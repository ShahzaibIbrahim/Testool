package com.webster.handler;

import com.webster.exception.ProcessException;
import com.webster.beans.Process;
import com.webster.beans.Task;
import com.webster.enums.ElementTypes;
import com.webster.enums.ResultTypes;
import com.webster.enums.TaskStatus;
import org.apache.commons.io.FileUtils;
import org.junit.ComparisonFailure;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class ProcessExecutionHandler {

    private WebDriver driver;
    private String failureSSPath;

    public ProcessExecutionHandler(String baseUrl, String failureSSPath) {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe"); // Is there a better way?
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        driver.get(baseUrl);

        this.failureSSPath = failureSSPath;
    }

    @Test
    public boolean executeProcess(Process process) {

        String actualResult = "";

        for (Task task : process.getTasks()) {

            try {
                WebElement element = getWebElement(task.getElementName(), task.getElementType());

                switch (task.getControlType()) {
                    case TEXT_BOX:
                        element.clear();
                        element.sendKeys(task.getFieldValue());
                        break;
                    case CHECK_BOX:
                    case BUTTON:
                        element.click();
                        break;
                    case DROP_DOWN:
                        Select dropdown = new Select(element);
                        dropdown.selectByValue(task.getFieldValue());
                        break;
                    default:
                        throw new ProcessException("Invalid Control Type Selected");
                }
                task.setStatusMessage(TaskStatus.SUCCESS.getMessage());
                task.setStatusCode(TaskStatus.SUCCESS.getStatus());

            } catch (Exception e) {
                task.setStatusCode(TaskStatus.FAILURE.getStatus());
                task.setStatusMessage(e.getMessage());
                process.setStatus(false);
                process.setStatusMessage("Process: " + process.getProcessName() + " Failed: " + "Please Correct All Task");
                return false;
            }
        }

        actualResult = getResult(process.getResultType());

        try {
            assertEquals(process.getExpectedResult(), actualResult);
            process.setStatus(true);
            process.setStatusMessage("Process: " + process.getProcessName() + " Executed SuccessFully");
            return true;
        } catch (ComparisonFailure exp) {
            process.setStatus(false);
            process.setStatusMessage("Process: " + process.getProcessName() + " Failed: " + exp.getMessage());

            try {
                takeSnapShot(this.failureSSPath);
            } catch (Exception e) {
                // do nothing for now
            }
            return false;
        } finally {
            driver.close();
        }

    }


    private WebElement getWebElement(String elementName, ElementTypes type) {
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
            case XPATH:
                element = driver.findElement(By.xpath(elementName));
        }

        return element;
    }

    private String getResult(ResultTypes resultType) {
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

    private void takeSnapShot(String fileWithPath) throws Exception {
        TakesScreenshot scrShot = ((TakesScreenshot) this.driver);
        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
        File DestFile = new File(fileWithPath);
        FileUtils.copyFile(SrcFile, DestFile);
    }

}
