package demo;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        plugin = {"pretty"},
        features = "classpath:features",
        glue = { "classpath:demo.stepdefinitions"},
        strict = true,
        snippets = CucumberOptions.SnippetType.CAMELCASE

)
public class CucumberTestSuite {

}


