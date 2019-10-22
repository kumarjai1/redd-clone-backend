package com.ga.dao;

import java.util.List;

import com.ga.entity.Post;

public interface PostDao {
	public Post createPost(Post post);
	public List<Post> listPosts();
}
