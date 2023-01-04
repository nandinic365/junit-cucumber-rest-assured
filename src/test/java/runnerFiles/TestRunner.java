package runnerFiles;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/features",
glue = {"stepDefinitions"},
tags = "",
monochrome = true,
plugin = {"pretty" , "html:target/HtmlReports.html",
		"json:target/JSONReports/report.json",
		"junit:target/JUnitReports/report.xml"} )

public class TestRunner {

}
