package com.ga.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ga.entity.Comment;
import com.ga.entity.JwtResponse;
import com.ga.entity.Post;
import com.ga.entity.User;
import com.ga.exception.EntityNotFoundException;
import com.ga.exception.LoginException;
import com.ga.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@Valid @RequestBody User user) {
		JwtResponse response = new JwtResponse(userService.signup(user));
		Map <String, String> mappedResponse = null;
		if (response.getToken() != null) {
			mappedResponse = new HashMap();
			mappedResponse.put("token", response.getToken());
			mappedResponse.put("username", userService.getUser().getUsername());
		}
		
		return ResponseEntity.ok(mappedResponse);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody User user) throws LoginException, EntityNotFoundException {
		JwtResponse response = new JwtResponse(userService.login(user));
		Map <String, String> mappedResponse = null;
		if (response.getToken() != null) {
			mappedResponse = new HashMap();
			mappedResponse.put("token", response.getToken());
			mappedResponse.put("username", userService.getUser().getUsername());
		}
		
		return ResponseEntity.ok(mappedResponse);
	}
	
	@GetMapping("/post")
	public List<Post> listPosts() {
		return userService.listPosts();
	}
	
	@GetMapping("/comment")
	public List<Comment> listComments() {
		return userService.listComments();
	}

}
