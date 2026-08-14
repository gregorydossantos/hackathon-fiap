package com.fiap.gregory.hackathon.integration;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        tags = "not @ignore",
        features = "src/test/resources/features",
        glue = {"steps", "configs"},
        snippets = CucumberOptions.SnippetType.CAMELCASE
)
public class RunCucumberTests {
}
