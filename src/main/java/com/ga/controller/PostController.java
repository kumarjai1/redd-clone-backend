package com.ga.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ga.entity.Post;
import com.ga.service.PostService;
import com.ga.service.UserService;

@RestController
@RequestMapping("/post")
public class PostController {

	@Autowired
	private PostService postService;
	@Autowired
	private UserService userService;
	
	@PostMapping
	public Post createPost(@RequestBody Post post) {
		
//		"we need a way to get the username from token"
		String username = userService.getUser().getUsername();
		return postService.createPost(username, post);
	}
}