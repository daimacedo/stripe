package runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;


@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", 
glue = { "stepdefinitions" }, 	
tags = { "@SendContactMail, @SendCV" }, 
plugin = {"json:target/cucumber-reports/json-report/cucumber.json"})
public class RunTest{

}
