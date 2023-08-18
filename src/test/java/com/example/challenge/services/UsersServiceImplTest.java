package com.example.challenge.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import com.example.challenge.entities.UserEntity;
import com.example.challenge.repository.interfaces.UserRepository;
import com.example.challenge.services.interfaces.UsersService;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UsersServiceImplTest {

    @Test
    public void testAddUser() {
        // Arrange
        UserEntity user = new UserEntity();
        UserRepository userRepositoryMock = mock(UserRepository.class);
        UsersService usersService = new UsersServiceImpl();
        
        when(userRepositoryMock.save(any(UserEntity.class))).thenReturn(user);
        
        // Act
        ResponseEntity<String> response = usersService.addUser(user);
        
        // Assert
        assertEquals(ResponseEntity.ok("ok"), response);
    }

    @Test
    public void testDeleteUser() {
        // Arrange
        long userId = 1L;
        UserRepository userRepositoryMock = mock(UserRepository.class);
        UsersService usersService = new UsersServiceImpl();
        
        doNothing().when(userRepositoryMock).deleteById(userId);
        
        // Act
        ResponseEntity<String> response = usersService.deleteUser(userId);
        
        // Assert
        assertEquals(ResponseEntity.ok("ok"), response);
    }

    @Test
    public void testModifyUser() {
        // Arrange
        long userId = 1L;
        UserEntity user = new UserEntity();
        UserRepository userRepositoryMock = mock(UserRepository.class);
        UsersService usersService = new UsersServiceImpl();
        
        when(userRepositoryMock.save(any(UserEntity.class))).thenReturn(user);
        
        // Act
        ResponseEntity<String> response = usersService.modifyUser(userId, user);
        
        // Assert
        assertEquals(ResponseEntity.ok("ok"), response);
        assertEquals(userId, user.getId());
    }

    @Test
    public void testGetUser() {
        // Arrange
        long userId = 1L;
        UserEntity user = new UserEntity();
        UserRepository userRepositoryMock = mock(UserRepository.class);
        UsersService usersService = new UsersServiceImpl();
        
        when(userRepositoryMock.findById(userId)).thenReturn(Optional.of(user));
        
        // Act
        ResponseEntity<UserEntity> response = usersService.getUser(userId);
        
        // Assert
        assertEquals(ResponseEntity.of(Optional.of(user)), response);
    }
}
