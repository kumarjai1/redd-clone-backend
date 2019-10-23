package com.ga.dao;

import java.util.List;

import com.ga.entity.Comment;
import com.ga.entity.Post;

public interface PostDao {
	public Post createPost(Post post);
	public List<Post> listPosts();
	public List<Comment> listComments(Long postId);
	public String deletePost(Long postId);
}
