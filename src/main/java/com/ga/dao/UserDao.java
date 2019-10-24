package com.ga.dao;

import java.util.List;

import com.ga.entity.Comment;
import com.ga.entity.Post;
import com.ga.entity.User;
import com.ga.exception.EntityNotFoundException;

public interface UserDao {
	
	public User signup (User user);
	public User login (User user) throws EntityNotFoundException;
	public User getUserByUsername(String username);
	public List<Post> listPosts(User user);
	public List<Comment> listComments(User user);
}
