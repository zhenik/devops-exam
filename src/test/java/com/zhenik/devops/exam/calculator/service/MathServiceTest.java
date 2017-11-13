package com.zhenik.devops.exam.calculator.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class MathServiceTest {

    private MathService mathService;
    private Double n1;
    private Double n2;

    @Before
    public void setup(){
        mathService = new MathService();
        n1 = 2d;
        n2 = 3d;
    }

    @After
    public void tearDown(){
        mathService = null;
        n1 = null;
        n2 = null;
    }

    @Test
    public void testAdd(){
        //Act
        Double result = mathService.add(n1,n2);

        //Assert
        assertEquals(5.0,result,0.1);
    }

    @Test
    public void testSubtract(){
        //Act
        Double result = mathService.subtract(n1,n2);

        //Assert
        assertEquals(-1.0,result,0.1);
    }

    @Test
    public void testDivide_NotZero(){
        //Act
        Double result = mathService.divide(n1,n2);

        //Assert
        assertEquals(2d/3d,result,0.1);
    }

    @Test
    public void testDivide_Zero(){
        //Arrange
        n2=0d;

        //Act
        Double result=null;
        try {
            result = mathService.divide(n1, n2);
            fail();
        } catch (ArithmeticException e){}

        //Assert
        assertNull(result);
    }

    @Test
    public void testMultiply(){
        //Act
        Double result = mathService.multiply(n1,n2);

        //Assert
        assertEquals(6d,result,0.1);
    }
}