package com.example.challenge.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.challenge.entities.UserEntity;
import com.example.challenge.services.UsersServiceImpl;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    @Autowired
    private UsersServiceImpl usersServiceImpl;

    @PostMapping("/addUser")
    public ResponseEntity<String> addUser(@RequestBody UserEntity user) {    	
    	return usersServiceImpl.addUser(user);
    }

    @DeleteMapping("/deleteUser/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
    	return usersServiceImpl.deleteUser(userId);
    }

    @GetMapping("/getUser/{userId}")
    public ResponseEntity<UserEntity> getUser(@PathVariable Long userId) {
        return usersServiceImpl.getUser(userId);
    }

    @PutMapping("/modifyUser/{userId}")
    public ResponseEntity<String> modifyUser(@PathVariable Long userId, @RequestBody UserEntity user) {
        return usersServiceImpl.modifyUser(userId, user);
    }
}