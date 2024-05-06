package com.jira;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitions {
    WebDriver driver;

    @Given("I open Jira login")
    public void openQuizTime() throws InterruptedException {
        Thread.sleep(1500);
        System.setProperty("webdriver.chrome.driver",
                "/Users/steven/Desktop/ChromeDriver/chromedriver-mac-x64/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.get(
                "https://schwsteven.atlassian.net/jira/software/projects/QUIZ/apps/3b2b1263-ab8b-4398-a3ae-3e8c97b07c01/11dbd3f5-e5c4-4850-88c9-9369b2ccb01a");
        driver.manage().window().maximize();
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
        submitButton.click();
        Thread.sleep(500);
        passwordInputField.sendKeys(password);
        submitButton.click();
    }

    @When("I click on 'Start new Game'")
    public void clickOnStartNewGame() throws InterruptedException {
        Thread.sleep(3000);

        WebElement iFrame = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(iFrame);
        WebElement startNewGameBtn = driver.findElement(By.id("startNewGameBtn"));
        startNewGameBtn.click();
    }

    @When("I answer {} questions correctly")
    public void answerCorrectly(int numOfQuestions) throws InterruptedException {
        Thread.sleep(1000);
        for (int i = 0; i < numOfQuestions; i++) {
            WebElement questionContainer = driver.findElement(By.className("question-container"));
            String currentQuestion = questionContainer.getText();
            String answer = Questions.Question.getAnswerForQuestion(currentQuestion);

            System.out.println("DISPLAYED QUESTION: " + currentQuestion);
            System.out.println("CORRECT ANSWER: " + answer);

            List<WebElement> answerButtons = driver.findElements(By.className("btn"));

            for (WebElement button : answerButtons) {
                if (button.getText().equals(answer)) {
                    Actions actions = new Actions(driver);
                    actions.moveToElement(button).perform();
                    Thread.sleep(300);
                    button.click();
                    break;
                }
            }
        }
    }

    @Then("The current score should be {}")
    public void checkScore(int expectedScore) throws InterruptedException {
        Thread.sleep(1000);
        WebElement currentScoreContainer = driver.findElement(By.className("score-container"));
        String actualScoreString = currentScoreContainer.getText();
        int actualScore = Integer.parseInt(actualScoreString.replaceAll("[^0-9]", ""));

        assertEquals(expectedScore, actualScore);
    }

    @Then("I get redirected to QuizTime URL")
    public void checkUrl() throws InterruptedException {
        Thread.sleep(5000);
        String expectedUrl = "https://schwsteven.atlassian.net/jira/software/projects/QUIZ/apps/3b2b1263-ab8b-4398-a3ae-3e8c97b07c01/11dbd3f5-e5c4-4850-88c9-9369b2ccb01a";
        String actualUrl = driver.getCurrentUrl();
        assertEquals(expectedUrl, actualUrl);
    }

    @Then("I should see the game field with all elements")
    public void checkGameField() throws InterruptedException {
        boolean result = true;

        try {
            WebElement questionContainer = driver.findElement(By.className("question-container"));
            WebElement scoreContainer = driver.findElement(By.className("score-container"));
            WebElement btnContainer = driver.findElement(By.className("btn-container"));
            List<WebElement> answerButtons = driver.findElements(By.className("btn"));

            if (answerButtons.size() != 4) {
                System.out.println("❌ Incorrect number of answer buttons.");
                throw new NoSuchElementException();
            }
            System.out.println("✅ Existence test passed: Expected elements were found.");
        } catch (Exception e) {
            System.out.println("❌ Existence test failed: Element was not found when it was expected to be existent.");
            result = false;
        }
        assertTrue(result);
    }

    @After
    public void quitTest() {
        if (driver != null) {
            driver.close();
        }
    }
}
