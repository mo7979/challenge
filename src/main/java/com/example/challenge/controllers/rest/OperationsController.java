package com.example.challenge.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.challenge.services.interfaces.OperationsService;

@RestController
@RequestMapping("/api/operations")
public class OperationsController {

    @Autowired 
    OperationsService operations;

    @GetMapping("/addition/{userId}")
    public ResponseEntity<String> addition(@PathVariable Long userId,@RequestParam double operand1, @RequestParam double operand2) throws Exception {
        return operations.addition(userId,operand1, operand2);
    }
    
    @GetMapping("/subtraction/{userId}")
    public ResponseEntity<String> subtraction(@PathVariable Long userId,@RequestParam double operand1, @RequestParam double operand2) throws Exception {
        return operations.subtraction(userId,operand1, operand2);
    }
    
    @GetMapping("/multiplication/{userId}")
    public ResponseEntity<String> multiplication(@PathVariable Long userId,@RequestParam double operand1, @RequestParam double operand2) throws Exception {
        return operations.multiplication(userId,operand1, operand2);
    }
    
    @GetMapping("/division/{userId}")
    public ResponseEntity<String> division(@PathVariable Long userId,@RequestParam double operand1, @RequestParam double operand2) throws Exception {
        return operations.division(userId,operand1, operand2);
    }
    
    @GetMapping("/squareRoot/{userId}")
    public ResponseEntity<String> squareRoot(@PathVariable Long userId,@RequestParam double operand1) throws Exception {
        return operations.squareRoot(userId,operand1);
    }
    
    @GetMapping("/randonStringGeneration/{userId}")
    public ResponseEntity<String> randonStringGeneration(@PathVariable Long userId) throws Exception {
        return operations.randonStringGeneration(userId);
    }
}



