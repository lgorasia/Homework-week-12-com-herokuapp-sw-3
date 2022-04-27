package testsuite;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

public class LoginTest extends Utility {
    String baseUrl = "http://the-internet.herokuapp.com/login";

    @Before
    public void openUP() {
        openBrowser(baseUrl);
    }

    @After
    public void tearDown() {
        closeBrowser();
    }

    @Test
    public void userSholdLoginSuccessfullyWithValidCredentials() {

        //locate username
        sendTextToElement(By.xpath("//input[@id='username']"), "tomsmith");

        //locate password
        sendTextToElement(By.xpath("//input[@id='password']"), "SuperSecretPassword!");

        //login button
        clickOnElement(By.tagName("i"));

        //this is requirement
        String expectedText = "Secure Area";
        // actual text
        String actualText = getTextFromElement(By.tagName("h2"));
        Assert.assertEquals(expectedText, actualText);
    }

    @Test
    public void verifyTheUsernameErrorMessage() {
        //locate username
        sendTextToElement(By.xpath("//input[@id='username']"), "tomsmith1");

        //locate password
        sendTextToElement(By.xpath("//input[@id='password']"), "SuperSecretPassword!");

        //login button
        clickOnElement(By.tagName("i"));

        //this is requirement
        String expectedText = "Your username is invalid!\n" +
                "×";
        // actual text
        String actualText = getTextFromElement(By.id("flash"));
        Assert.assertEquals(expectedText, actualText);

    }

    @Test
    public void verifyThePasswordErrorMessage() {
        //locate username
        sendTextToElement(By.xpath("//input[@id='username']"), "tomsmith1");

        //locate password
        sendTextToElement(By.xpath("//input[@id='password']"), "SuperSecretPassword");

        //login button
        clickOnElement(By.tagName("i"));

        //this is requirement
        String expectedText = "Your username is invalid!\n" +
                "×";
        // actual text
        WebElement actualTextElement = driver.findElement(By.xpath("//div[@id='flash']"));
        String actualText = actualTextElement.getText();
        Assert.assertEquals(expectedText, actualText);
    }
}
