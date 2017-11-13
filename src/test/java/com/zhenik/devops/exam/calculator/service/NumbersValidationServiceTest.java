package com.zhenik.devops.exam.calculator.service;

import com.zhenik.devops.exam.calculator.domain.dto.Numbers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class NumbersValidationServiceTest {

    private NumbersValidationService service;

    @Before
    public void setup(){
        service=new NumbersValidationService();
    }

    @After
    public void tearDown(){
        service=null;
    }

    @Test
    public void testIsValid(){
        // Arrange
        Numbers numbers = new Numbers();

        //Act
        boolean valid = service.isNumbersValid(numbers);

        //Assert
        assertFalse(valid);
    }

}