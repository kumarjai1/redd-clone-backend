package com.ga.exception;

import static org.mockito.Mockito.when;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import com.ga.dao.UserDao;
import com.ga.entity.User;
import com.ga.service.UserServiceImpl;

public class LoginExceptionTest {
	
	@Rule
    public MockitoRule rule = MockitoJUnit.rule();
	
	@InjectMocks
	private UserServiceImpl userService;
	
//	@InjectMocks
//	private EntityNotFoundException entityNotFound;
	
	@Mock
	private UserDao userDao;
	
	@InjectMocks
	private User user;
	
	@Test(expected = LoginException.class)
	public void loginException_Test() throws LoginException, EntityNotFoundException {
		when(userDao.login(user)).thenThrow(new RuntimeException());
		User user = new User();
		userService.login(user);
	}
}
