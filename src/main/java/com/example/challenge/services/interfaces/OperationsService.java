package com.example.challenge.services.interfaces;

import org.springframework.http.ResponseEntity;

public interface OperationsService {
	public ResponseEntity<String> addition(final long userId, final double operand1,final double operand2) throws Exception;
	public ResponseEntity<String> subtraction(final long userId, final double operand1,final double operand2) throws Exception;
	public ResponseEntity<String> multiplication(final long userId, final double operand1,final double operand2) throws Exception;
	public ResponseEntity<String> division(final long userId, final double operand1,final double operand2) throws Exception;
	public ResponseEntity<String> squareRoot(final long userId, final double operand1) throws Exception;
	public ResponseEntity<String> randonStringGeneration(final long userId) throws Exception;
}
