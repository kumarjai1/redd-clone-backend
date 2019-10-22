package com.ga.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ga.entity.Comment;
import com.ga.service.CommentService;
import com.ga.service.UserService;

@RestController
@RequestMapping ("/comment")
public class CommentController {
	
	@Autowired
	private UserService userService;
	
	@Autowired 
	private CommentService commentService;
	
	@PostMapping("/{postId}")
	public Comment createComment(@RequestBody Comment comment, @PathVariable Long postId) {
		return commentService.createComment(userService.getUser(), postId, comment);
	}
	
	
}
