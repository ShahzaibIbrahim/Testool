package com.webster.handler;

import com.webster.ProcessException;
import com.webster.beans.Process;
import com.webster.beans.Task;
import com.webster.testool.Testool;
import org.junit.ComparisonFailure;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static org.junit.Assert.assertEquals;

public class ProcessExecutionHandler {

    @Test
    public boolean executeProcess(Process process, String baseUrl) {
        Testool testool = new Testool(baseUrl);
        String actualResult = "";

        for (Task task : process.getTasks()) {

            try {

                WebElement element = testool.getWebElement(task.getElementName(), task.getElementType());

                switch (task.getControlType()) {
                    case TEXT_BOX:
                        element.clear();// Do something
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
                task.setStatusMessage("Task Executed");

            } catch (Exception e) {
                task.setStatusMessage(e.getLocalizedMessage()); // TODO: Also Set Status
                process.setStatus(false);
                process.setStatusMessage("Process: " + process.getProcessName() + " Failed: " + "Please Correct All Task");
                return false;
            }
        }

        actualResult = testool.getResult(process.getResultType());

        try {
            assertEquals(process.getExpectedResult(), actualResult);
            process.setStatus(true);
            process.setStatusMessage("Process: " + process.getProcessName() + " Executed SuccessFully");
            return true;
        } catch (ComparisonFailure exp) {
            process.setStatus(false);
            process.setStatusMessage("Process: " + process.getProcessName() + " Failed: " + exp.getMessage());
            return false;
        }

    }

}
