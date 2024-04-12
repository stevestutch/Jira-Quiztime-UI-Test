package com.jira;

import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitions {
    WebDriver driver;

    @Given("I open Jira login")
    public void openQuizTime() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver",
                "/Users/steven/Desktop/ChromeDriver/chromedriver-mac-x64/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.get(
                "https://schwsteven.atlassian.net/jira/software/projects/QUIZ/apps/3b2b1263-ab8b-4398-a3ae-3e8c97b07c01/11dbd3f5-e5c4-4850-88c9-9369b2ccb01a");
    }

    @When("I enter valid credentials")
    public void enterCredentials() throws InterruptedException {
        Thread.sleep(1500);

        WebElement userNameInputField = driver.findElement(By.id("username"));
        WebElement submitButton = driver.findElement(By.id("login-submit"));
        WebElement passwordInputField = driver.findElement(By.id("password"));

        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("properties/config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String userName = properties.getProperty("jira.username");
        String password = properties.getProperty("jira.password");

        userNameInputField.sendKeys(userName);
        Thread.sleep(500);
        submitButton.click();
        Thread.sleep(500);
        passwordInputField.sendKeys(password);
        Thread.sleep(500);
        submitButton.click();
    }

    @Then("I get redirected to QuizTime URL")
    public void checkUrl() throws InterruptedException {
        Thread.sleep(3000);
        String expectedUrl = "https://schwsteven.atlassian.net/jira/software/projects/QUIZ/apps/3b2b1263-ab8b-4398-a3ae-3e8c97b07c01/11dbd3f5-e5c4-4850-88c9-9369b2ccb01a";
        String actualUrl = driver.getCurrentUrl();
        assertEquals(expectedUrl, actualUrl);
    }
}
