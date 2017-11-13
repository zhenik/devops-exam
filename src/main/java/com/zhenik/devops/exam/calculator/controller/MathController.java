package com.zhenik.devops.exam.calculator.controller;


import com.zhenik.devops.exam.calculator.domain.dto.Numbers;
import com.zhenik.devops.exam.calculator.service.MathService;
import com.zhenik.devops.exam.calculator.service.NumbersValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MathController {

    @Autowired
    String appName;

    @Autowired
    MathService mathService;

    @Autowired
    NumbersValidationService validationService;

    @GetMapping(path = "/health-check", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> check() {
        String up = appName + " is UP";
        return ResponseEntity.ok(up);
    }

    private String getResult(Double result){
        return "instance: "+appName+"\nresult: "+result;
    }

    @PostMapping(path = "/math/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> add(@RequestBody Numbers numbers){
        if (!validationService.isNumbersValid(numbers)){
            return ResponseEntity.status(404).build();
        }
        double result = mathService.add(numbers.getN1(),numbers.getN2());
        return ResponseEntity.ok(getResult(result));
    }

    @PostMapping(path = "/math/subtract", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> subtract(@RequestBody Numbers numbers){
        if (!validationService.isNumbersValid(numbers)){
            return ResponseEntity.status(404).build();
        }
        double result = mathService.subtract(numbers.getN1(),numbers.getN2());
        return ResponseEntity.ok(getResult(result));
    }

    @PostMapping(path = "/math/divide", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> divide(@RequestBody Numbers numbers){
        if (!validationService.isNumbersValid(numbers)){
            return ResponseEntity.status(404).build();
        }
        try {
            double result = mathService.divide(numbers.getN1(),numbers.getN2());
            return ResponseEntity.ok(getResult(result));
        } catch (ArithmeticException e){
            // divide by 0
            return ResponseEntity.status(404).build();
        }
    }

    @PostMapping(path = "/math/multiply", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> multiply(@RequestBody Numbers numbers){
        if (!validationService.isNumbersValid(numbers)){
            return ResponseEntity.status(404).build();
        }
        double result = mathService.multiply(numbers.getN1(),numbers.getN2());
        return ResponseEntity.ok(getResult(result));
    }
}
