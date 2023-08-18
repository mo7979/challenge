package com.example.challenge.services.interfaces;

import org.springframework.http.ResponseEntity;

import com.example.challenge.entities.UserEntity;

public interface UsersService {
	public ResponseEntity<String> addUser(final UserEntity user);
	public ResponseEntity<String> deleteUser(final long userId);
	public ResponseEntity<String> modifyUser(final long userId, final UserEntity user);
	public ResponseEntity<UserEntity> getUser(final long userId);
}
