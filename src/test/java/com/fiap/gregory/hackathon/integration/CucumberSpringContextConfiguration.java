package com.fiap.gregory.hackathon.integration;

import com.fiap.gregory.hackathon.HackathonApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = HackathonApplication.class)
public class CucumberSpringContextConfiguration {
}
