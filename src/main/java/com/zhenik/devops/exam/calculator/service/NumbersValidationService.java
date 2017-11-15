package com.zhenik.devops.exam.calculator.service;

import com.zhenik.devops.exam.calculator.domain.dto.Numbers;
import org.springframework.stereotype.Service;

/**
 * */
@Service
public class NumbersValidationService {

    /**
     * */
    public boolean isNumbersValid(Numbers numbers) {
        return (numbers != null && numbers.getN1() != null && numbers.getN2() != null);
    }
}
