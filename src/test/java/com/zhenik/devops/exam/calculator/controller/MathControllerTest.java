package com.zhenik.devops.exam.calculator.controller;

import com.zhenik.devops.exam.calculator.domain.dto.Numbers;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;
import org.springframework.http.MediaType;


import static org.junit.Assert.*;


public class MathControllerTest extends ControllerTestBase{


    @Test
    public void testHealthCheck(){

        // Act
        Response response = RestAssured
                .given().accept(MediaType.TEXT_PLAIN_VALUE)
                .get("/health-check");

        // Assert
        assertEquals(200, response.statusCode());
        assertTrue(response.getBody().print().contains("UP"));

    }

    @Test
    public void testAdd(){

        // Act
        Response response = RestAssured
                .given()
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .body(new Numbers(2d,3d))
                .post("/math/add");

        // Assert
        assertEquals(200, response.statusCode());
        assertTrue(response.getBody().print().contains("result: 5.0"));
    }

    @Test
    public void testSubtract(){

        // Act
        Response response = RestAssured
                .given()
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .body(new Numbers(2d,3d))
                .post("/math/subtract");

        // Assert
        assertEquals(200, response.statusCode());
        assertTrue(response.getBody().print().contains("result: -1.0"));
    }

    @Test
    public void testMultiply(){

        // Act
        Response response = RestAssured
                .given()
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .body(new Numbers(2d,3d))
                .post("/math/multiply");

        // Assert
        assertEquals(200, response.statusCode());
        assertTrue(response.getBody().print().contains("result: 6.0"));
    }

    @Test
    public void testDivide(){
        // Act
        Response response = RestAssured
                .given()
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .body(new Numbers(6d,2d))
                .post("/math/divide");

        // Assert
        assertEquals(200, response.statusCode());
        assertTrue(response.getBody().print().contains("result: 3.0"));
    }

    @Test
    public void testDivideByZero(){
        // Act
        Response response = RestAssured
                .given()
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .body(new Numbers(6d,0d))
                .post("/math/divide");

        // Assert
        assertEquals(404, response.statusCode());
    }

    @Test
    public void notValidJson(){
        Numbers numbers = new Numbers();
        // Act
        Response response1 = RestAssured
                .given()
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .body(numbers)
                .post("/math/subtract");

        numbers.setN1(2d);
        Response response2 = RestAssured
                .given()
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .body(numbers)
                .post("/math/subtract");

        numbers.setN1(null);
        numbers.setN2(3d);

        Response response3 = RestAssured
                .given()
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .body(numbers)
                .post("/math/subtract");


        // Assert
        assertEquals(404, response1.statusCode());
        assertEquals(404, response2.statusCode());
        assertEquals(404, response3.statusCode());
    }

}