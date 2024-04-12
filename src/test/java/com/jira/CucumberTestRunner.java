package com.jira;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/com/jira/features", glue = { "com.jira" })
public class CucumberTestRunner {
    public static void main(String[] args) {
    }
}
