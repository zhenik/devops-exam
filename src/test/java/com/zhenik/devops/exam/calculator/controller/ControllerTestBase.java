package com.zhenik.devops.exam.calculator.controller;

import com.zhenik.devops.exam.calculator.CalculatorApplication;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.logging.Level;
import java.util.logging.Logger;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CalculatorApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
abstract class ControllerTestBase {
    private Logger logger = Logger.getLogger(ControllerTestBase.class.getCanonicalName());

    @LocalServerPort
    protected int port = 0;

    @Before
    @After
    public void cleanAndCheck() {
        logger.log(Level.INFO, String.valueOf(port));

        // RestAssured configs shared by all the tests
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
        RestAssured.basePath = "/";
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }


}
