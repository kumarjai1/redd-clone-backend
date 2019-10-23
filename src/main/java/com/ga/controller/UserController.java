package com.ga.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ga.entity.JwtResponse;
import com.ga.entity.User;
import com.ga.exception.EntityNotFoundException;
import com.ga.exception.LoginException;
import com.ga.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/hello")
	public String helloWorld() {
		return "Hello World";
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@Valid @RequestBody User user) {
		return ResponseEntity.ok(new JwtResponse(userService.signup(user)));
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody User user) throws LoginException, EntityNotFoundException {
		return ResponseEntity.ok(new JwtResponse(userService.login(user)));
	}

}
