package com.ga.service;

import java.util.List;

import com.ga.entity.Comment;
import com.ga.entity.Post;

public interface PostService {
	public Post createPost(String username, Post post);
	public List<Post> listPosts();
	public List<Comment> listComments(Long postId);
}
