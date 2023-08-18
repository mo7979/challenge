package com.example.challenge.services;

import java.nio.charset.Charset;
import java.util.Date;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.challenge.entities.UserEntity;

import com.example.challenge.entities.OperationEntity;
import com.example.challenge.entities.RecordEntity;
import com.example.challenge.repository.interfaces.OperationRepository;
import com.example.challenge.repository.interfaces.RecordRepository;
import com.example.challenge.repository.interfaces.UserRepository;
import com.example.challenge.services.interfaces.OperationsService;

@Service
public class OperationsServiceImpl implements OperationsService {

	@Autowired
	RecordRepository recordRepository;
	
	@Autowired
	OperationRepository operationRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public ResponseEntity<String> addition(final long userId, double operand1, double operand2) throws Exception {
		return operation(userId,operand1,operand2,OPERAND.ADDITION);		
	}

	@Override
	public ResponseEntity<String> subtraction(final long userId, double operand1, double operand2) throws Exception {
		return operation(userId,operand1,operand2,OPERAND.SUBTRACTION);
	}

	@Override
	public ResponseEntity<String> multiplication(final long userId, double operand1, double operand2) throws Exception {
		return operation(userId,operand1,operand2,OPERAND.MULTIPLICATION);
	}

	@Override
	public ResponseEntity<String> division(final long userId, double operand1, double operand2) throws Exception {
		return operation(userId,operand1,operand2,OPERAND.DIVISION);
	}

	@Override
	public ResponseEntity<String> squareRoot(final long userId, double operand1) throws Exception {
		return operation(userId,operand1,-1,OPERAND.SQUARE_ROOT);
	}

	@Override
	public ResponseEntity<String> randonStringGeneration(final long userId) throws Exception {
		return (ResponseEntity<String>) operation(userId,-1,-1,OPERAND.RANDOM_STRING_GENERATION);
	}
		
	private ResponseEntity<String> operation(final long userId, final double operand1, final double operand2, final OPERAND operand) throws Exception {
		
		
		Optional<OperationEntity> operationEntity = operationRepository.findById((long)operand.ordinal() + 1);		
		Optional<UserEntity> userEntity = userRepository.findById(userId);
		Optional<RecordEntity> recordEntity = recordRepository.findTopByOrderByDateDesc();
		double balance = userEntity.get().getUserBalance();
		if(recordEntity.isPresent()) {
			balance = recordEntity.get().getUserBalance();
		}
		
		final RecordEntity record_ = new RecordEntity();
		record_.setAmount(operationEntity.get().getCost());
		record_.setOperation(operationEntity.get());
		record_.setDate(new Date());
		record_.setUser(userEntity.get());
		record_.setUserBalance(balance - operationEntity.get().getCost());
		
		recordRepository.save(record_);
				
		userEntity.get().setUserBalance(balance);
		
		userRepository.save(userEntity.get());		
		
		boolean userHasBalance = record_.getUserBalance()>0;
		if(!userHasBalance) {
			return new ResponseEntity<String>("No current balance available: $" + balance, HttpStatus.OK);
		}		
		
		ResponseEntity<String> res;
		if(operand==OPERAND.RANDOM_STRING_GENERATION) {
			
			byte[] array = new byte[7]; // length is bounded by 7
		    new Random().nextBytes(array);
		    String generatedString = new String(array, Charset.forName("UTF-8"));
		    
			res = new ResponseEntity<String>("Current balance available: $" + balance + ", result: " + generatedString, HttpStatus.OK);
			
		} else {
			
			double result = 0;
			switch(operand) {
				case ADDITION:
					result = operand1 + operand2;
					break;
				case SUBTRACTION:
					result = operand1 - operand2;
					break;
				case MULTIPLICATION:
					result = operand1 * operand2;
					break;
				case DIVISION:
					result = operand1 / operand2;
					break;
				case SQUARE_ROOT:
					result = operand1 * operand1;
					break;
				default:
					throw new Exception("Invalid OPERATION option");
			}
			
			final String res_ = "Current balance available: $" + balance + ", result: " + result;
			res = new ResponseEntity<String>(res_, HttpStatus.OK);
			
			record_.setOperationResponse(res_);
			recordRepository.save(record_);
		}		
		
		record_.setUserBalance(record_.getUserBalance() - record_.getAmount());
		
		return res;
	}
	
	private enum OPERAND {
		ADDITION,
		SUBTRACTION,
		DIVISION,			
		SQUARE_ROOT,
		RANDOM_STRING_GENERATION,
		MULTIPLICATION
	}
}
