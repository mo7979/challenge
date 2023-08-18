package com.example.challenge.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.challenge.entities.RecordEntity;
import com.example.challenge.entities.UserEntity;
import com.example.challenge.repository.interfaces.RecordRepository;
import com.example.challenge.repository.interfaces.UserRepository;

@Controller
public class WebController {

	@Autowired
	private RecordRepository recordRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/dashboard")
    public String dashboard(Model model) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            
        	final List<RecordEntity> records = recordRepository.findAll();
        	
        	final Optional<UserEntity> user = userRepository.findByUsername("admin");
        	
        	double balance = user.get().getUserBalance();
        	final Optional<RecordEntity> recordEntity = recordRepository.findTopByOrderByDateDesc();
        	if(recordEntity.isPresent()) {
        		balance = recordEntity.get().getUserBalance(); 
        	}
        	
        	model.addAttribute("balance", balance); // Agregar userId al modelo
            model.addAttribute("records", records); // Agregar userId al modelo
            model.addAttribute("userId", 1); // Agregar userId al modelo
        }
        
        return "dashboard";
    }
	
	@GetMapping("/login")
    public String login(){
        return "login";
    }
}
