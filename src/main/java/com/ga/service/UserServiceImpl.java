package com.ga.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ga.config.JwtUtil;
import com.ga.dao.UserDao;
import com.ga.entity.Comment;
import com.ga.entity.Post;
import com.ga.entity.User;
import com.ga.exception.EntityNotFoundException;
import com.ga.exception.LoginException;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	@Qualifier("encoder")
	PasswordEncoder bCryptPasswordEncoder;
	
	@Autowired 
	JwtUtil jwtUtil;
	
	private User user;
	
	//just passing username - to refactor
	private String username;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userDao.getUserByUsername(username);
		//this saves the current user to be returned later
		this.user = user;
		
		//passing username only to possibly refactor
		this.username = username;
		if (user == null) throw new UsernameNotFoundException("Unknown usernamer " + username);
		return new org.springframework.security.core.userdetails.User
				(user.getUsername(), bCryptPasswordEncoder.encode(user.getPassword()), true, true, true, true, getGrantedAuthorities(user));
	}

	private List<GrantedAuthority> getGrantedAuthorities(User user) {
		// TODO Auto-generated method stub
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		//authorities.add(new SimpleGrantedAuthority(user.getUserRole().getName()));
		
		return authorities;
	}

	@Override
	public String signup(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

		if(userDao.signup(user).getUserId() != null) {
			UserDetails userDetails = loadUserByUsername(user.getUsername());
			
			return jwtUtil.generateToken(userDetails);
		}
		return null;
	}

	@Override
	public String login(User user) throws LoginException, EntityNotFoundException {
		User foundUser = userDao.login(user);
		if (foundUser != null &&  
				foundUser.getUserId() != null && 
				bCryptPasswordEncoder.matches(user.getPassword(), foundUser.getPassword())) {
			UserDetails userDetails = loadUserByUsername(foundUser.getUsername());
			return jwtUtil.generateToken(userDetails);
		}
		throw new LoginException("Username/password incorrect");
		
	}
	
	public User getUser() {
		return user;
	}
	public String getUsername() {
		return username;
	}

	@Override
	public List<Post> listPosts() {
		return userDao.listPosts(getUser());
	}

	@Override
	public List<Comment> listComments() {
		// TODO Auto-generated method stub
		return userDao.listComments(getUser());
	}

}
