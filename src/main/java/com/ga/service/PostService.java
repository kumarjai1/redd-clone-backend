package com.ga.service;

import java.util.List;

import com.ga.entity.Post;

public interface PostService {
	public Post createPost(String username, Post post);
	public List<Post> listPosts();
}
