package com.ga.dao;

import com.ga.entity.User;
import com.ga.exception.EntityNotFoundException;

public interface UserDao {
	
	public User signup (User user);
	public User login (User user) throws EntityNotFoundException;
	public User getUserByUsername(String username);
}
