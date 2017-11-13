package com.zhenik.devops.exam.calculator.service;

import org.springframework.stereotype.Service;

@Service
public class MathService {

    public double add(double n1, double n2) {
        return n1+n2;
    }

    public double subtract(double n1, double n2) {
        return n1-n2;
    }

    public double divide(double n1, double n2) throws ArithmeticException {
        if (n2==0d){ throw new ArithmeticException(); }
        return n1/n2;
    }

    public double multiply(double n1, double n2) {
        return n1*n2;
    }
}

