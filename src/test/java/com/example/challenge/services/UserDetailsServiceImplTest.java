package com.example.challenge.services;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.challenge.entities.RoleEntity;
import com.example.challenge.entities.UserEntity;
import com.example.challenge.repository.interfaces.UserRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserDetailsServiceImplTest {

    @Test
    public void testLoadUserByUsername() {
        // Arrange
        String username = "testUser";
        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setPassword("testPassword");
        Set<RoleEntity> roles = new HashSet<>();
        RoleEntity role = new RoleEntity();
        role.setName("ROLE_USER");
        roles.add(role);
        user.setRoles(roles);
        
        UserRepository userRepositoryMock = mock(UserRepository.class);
        UserDetailsServiceImpl userDetailsService = new UserDetailsServiceImpl();
        
        when(userRepositoryMock.findByUsername(username)).thenReturn(Optional.of(user));
        
        // Act
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        
        // Assert
        assertEquals(username, userDetails.getUsername());
        assertEquals(user.getPassword(), userDetails.getPassword());
        assertEquals(1, userDetails.getAuthorities().size());
    }

    @Test
    public void testLoadUserByUsername_UserNotFound() {
        // Arrange
        String username = "nonExistentUser";
        UserRepository userRepositoryMock = mock(UserRepository.class);
        UserDetailsServiceImpl userDetailsService = new UserDetailsServiceImpl();
        
        when(userRepositoryMock.findByUsername(username)).thenReturn(Optional.empty());
        
        // Act and Assert
        assertThrows(UsernameNotFoundException.class, () -> userDetailsService.loadUserByUsername(username));
    }
}
