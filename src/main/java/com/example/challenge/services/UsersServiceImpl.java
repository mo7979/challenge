package com.example.challenge.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.challenge.entities.UserEntity;
import com.example.challenge.repository.interfaces.UserRepository;
import com.example.challenge.services.interfaces.UsersService;

@Service
public class UsersServiceImpl implements UsersService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public ResponseEntity<String> addUser(UserEntity user) {
		userRepository.save(user);
		return ResponseEntity.ok("ok");
	}

	@Override
	public ResponseEntity<String> deleteUser(long userId) {		
		userRepository.deleteById(userId);		
		return ResponseEntity.ok("ok");
	}

	@Override
	public ResponseEntity<String> modifyUser(long userId, UserEntity user) {
		user.setId(userId);
		userRepository.save(user);
		return ResponseEntity.ok("ok");
	}

	@Override
	public ResponseEntity<UserEntity> getUser(long userId) {
		final Optional<UserEntity> user = userRepository.findById(userId);
		if(user.isPresent()) {
			return ResponseEntity.of(user);
		} else {
			return ResponseEntity.of(null);
		}
	}
}
