package com.ga.exception;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import com.ga.dao.UserDao;
import com.ga.entity.User;
import com.ga.service.UserServiceImpl;

import static org.mockito.Mockito.when;


public class EntityNotFoundExceptionTest {
	
	@Rule
    public MockitoRule rule = MockitoJUnit.rule();
	
	@InjectMocks
	private UserServiceImpl userService;
	
	@Mock
	private UserDao userDao;
	
	@InjectMocks
	private User user;
	
	@Test(expected=LoginException.class)
	public void loginException_Test() throws LoginException, EntityNotFoundException{
		when(userDao.login(user)).thenThrow(new RuntimeException());
		User user = new User();
		userService.login(user);
	}
}
