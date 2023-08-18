package com.example.challenge.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.challenge.entities.OperationEntity;
import com.example.challenge.entities.RecordEntity;
import com.example.challenge.entities.UserEntity;
import com.example.challenge.repository.interfaces.OperationRepository;
import com.example.challenge.repository.interfaces.RecordRepository;
import com.example.challenge.repository.interfaces.UserRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

public class OperationsServiceImplTest {

    @Test
    public void testAddition() throws Exception {
        // Arrange
        double operand1 = 5;
        double operand2 = 3;
        double userBalance = 10;
        
        UserRepository userRepositoryMock = mock(UserRepository.class);
        when(userRepositoryMock.findById(anyLong())).thenReturn(Optional.of(new UserEntity()));
        
        OperationRepository operationRepositoryMock = mock(OperationRepository.class);
        when(operationRepositoryMock.findById(anyLong())).thenReturn(Optional.of(new OperationEntity()));
        
        RecordRepository recordRepositoryMock = mock(RecordRepository.class);
        
        OperationsServiceImpl operationsService = new OperationsServiceImpl();
        
        // Act
        ResponseEntity<String> result = operationsService.addition(1L, operand1, operand2);
        
        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(userRepositoryMock, times(1)).save(any(UserEntity.class));
        verify(recordRepositoryMock, times(1)).save(any(RecordEntity.class));
        
        String expectedResponse = "Current balance available: $" + (userBalance - 2.0) + ", result: " + (operand1 + operand2);
        assertEquals(expectedResponse, result.getBody());
    }

    @Test
    public void testSubtraction() throws Exception {
        // Arrange
        double operand1 = 8;
        double operand2 = 3;
        double userBalance = 15;
        
        UserRepository userRepositoryMock = mock(UserRepository.class);
        when(userRepositoryMock.findById(anyLong())).thenReturn(Optional.of(new UserEntity()));
        
        OperationRepository operationRepositoryMock = mock(OperationRepository.class);
        when(operationRepositoryMock.findById(anyLong())).thenReturn(Optional.of(new OperationEntity()));
        
        RecordRepository recordRepositoryMock = mock(RecordRepository.class);
        
        OperationsServiceImpl operationsService = new OperationsServiceImpl();
        
        // Act
        ResponseEntity<String> result = operationsService.subtraction(1L, operand1, operand2);
        
        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(userRepositoryMock, times(1)).save(any(UserEntity.class));
        verify(recordRepositoryMock, times(1)).save(any(RecordEntity.class));
        
        String expectedResponse = "Current balance available: $" + (userBalance - 1.5) + ", result: " + (operand1 - operand2);
        assertEquals(expectedResponse, result.getBody());
    }
    
    @Test
    public void testMultiplication() throws Exception {
        // Arrange
        double operand1 = 4;
        double operand2 = 3;
        double userBalance = 20;
        
        UserRepository userRepositoryMock = mock(UserRepository.class);
        when(userRepositoryMock.findById(anyLong())).thenReturn(Optional.of(new UserEntity()));
        
        OperationRepository operationRepositoryMock = mock(OperationRepository.class);
        when(operationRepositoryMock.findById(anyLong())).thenReturn(Optional.of(new OperationEntity()));
        
        RecordRepository recordRepositoryMock = mock(RecordRepository.class);
        
        OperationsServiceImpl operationsService = new OperationsServiceImpl();
        
        // Act
        ResponseEntity<String> result = operationsService.multiplication(1L, operand1, operand2);
        
        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(userRepositoryMock, times(1)).save(any(UserEntity.class));
        verify(recordRepositoryMock, times(1)).save(any(RecordEntity.class));
        
        String expectedResponse = "Current balance available: $" + (userBalance - 2.0) + ", result: " + (operand1 * operand2);
        assertEquals(expectedResponse, result.getBody());
    }
    
    @Test
    public void testDivision() throws Exception {
        // Arrange
        double operand1 = 10;
        double operand2 = 2;
        double userBalance = 30;
        
        UserRepository userRepositoryMock = mock(UserRepository.class);
        when(userRepositoryMock.findById(anyLong())).thenReturn(Optional.of(new UserEntity()));
        
        OperationRepository operationRepositoryMock = mock(OperationRepository.class);
        when(operationRepositoryMock.findById(anyLong())).thenReturn(Optional.of(new OperationEntity()));
        
        RecordRepository recordRepositoryMock = mock(RecordRepository.class);
        
        OperationsServiceImpl operationsService = new OperationsServiceImpl();
        
        // Act
        ResponseEntity<String> result = operationsService.division(1L, operand1, operand2);
        
        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(userRepositoryMock, times(1)).save(any(UserEntity.class));
        verify(recordRepositoryMock, times(1)).save(any(RecordEntity.class));
        
        String expectedResponse = "Current balance available: $" + (userBalance - 3.0) + ", result: " + (operand1 / operand2);
        assertEquals(expectedResponse, result.getBody());
    }
    
    @Test
    public void testSquareRoot() throws Exception {
        // Arrange
        double operand1 = 9;
        double userBalance = 40;
        
        UserRepository userRepositoryMock = mock(UserRepository.class);
        when(userRepositoryMock.findById(anyLong())).thenReturn(Optional.of(new UserEntity()));
        
        OperationRepository operationRepositoryMock = mock(OperationRepository.class);
        when(operationRepositoryMock.findById(anyLong())).thenReturn(Optional.of(new OperationEntity()));
        
        RecordRepository recordRepositoryMock = mock(RecordRepository.class);
        
        OperationsServiceImpl operationsService = new OperationsServiceImpl();
        
        // Act
        ResponseEntity<String> result = operationsService.squareRoot(1L, operand1);
        
        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(userRepositoryMock, times(1)).save(any(UserEntity.class));
        verify(recordRepositoryMock, times(1)).save(any(RecordEntity.class));
        
        String expectedResponse = "Current balance available: $" + (userBalance - 2.5) + ", result: " + (operand1 * operand1);
        assertEquals(expectedResponse, result.getBody());
    }
    
    @Test
    public void testRandomStringGeneration() throws Exception {
        // Arrange
        double userBalance = 50;
        
        UserRepository userRepositoryMock = mock(UserRepository.class);
        when(userRepositoryMock.findById(anyLong())).thenReturn(Optional.of(new UserEntity()));
        
        OperationRepository operationRepositoryMock = mock(OperationRepository.class);
        when(operationRepositoryMock.findById(anyLong())).thenReturn(Optional.of(new OperationEntity()));
        
        RecordRepository recordRepositoryMock = mock(RecordRepository.class);
        
        OperationsServiceImpl operationsService = new OperationsServiceImpl();
        
        // Act
        ResponseEntity<String> result = operationsService.randonStringGeneration(1L);
        
        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(userRepositoryMock, times(1)).save(any(UserEntity.class));
        verify(recordRepositoryMock, times(1)).save(any(RecordEntity.class));
        
        String expectedResponse = "Current balance available: $" + (userBalance - 5.0) + ", result: <generated random string>";
        assertEquals(expectedResponse, result.getBody());
    }
}
