package com.ga.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ga.config.JwtUtil;
import com.ga.dao.UserDao;
import com.ga.entity.User;
import com.ga.exception.EntityNotFoundException;
import com.ga.exception.LoginException;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class UserServiceTest { 
	
	@InjectMocks
    private UserServiceImpl userService;
    
    @InjectMocks
    private User user;
    
	@Mock
    UserDao userDao;
    
    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private PasswordEncoder bCryptPasswordEncoder;
    
    @Before
    public void initMocks() {
      MockitoAnnotations.initMocks(this);
    }
    
    @Before
    public void initializeDummyUser() {
   
        user.setUserId(1L);
        user.setUsername("test");
        user.setEmail("test@test.com");
        user.setPassword("test");
    }
     
//    @Test
//    public void updateUser_User_Success() {
//        when(userDao.updateUser(any(), anyLong())).thenReturn(user);
//
//        User tempUser = userService.updateUser(user, user.getUserId());
//
//        assertEquals(tempUser.getUsername(), user.getUsername());
//    }
    
    @Test
    public void signup_ReturnsJwtAndUser_Success() {
        String expectedToken = "12345";
        
        when(userDao.signup(any())).thenReturn(user);
        when(userDao.getUserByUsername(anyString())).thenReturn(user);
        when(jwtUtil.generateToken(any())).thenReturn(expectedToken);
        when(bCryptPasswordEncoder.encode(user.getPassword())).thenReturn("test");
        
        String actualToken = userService.signup(user);
        
        assertEquals(actualToken, expectedToken);
    }
    
    @Test
    public void signup_UserNotFound_Error() {
    	
    	User tempUser = user;
    	tempUser.setUserId(null);

        when(userDao.signup(any())).thenReturn(tempUser);

        String token = userService.signup(user);

        assertEquals(token, null);
    }
     
    @Test
    public void login_ReturnsJwt_Success() throws EntityNotFoundException, LoginException {
        String expectedToken = "12345";
        
        when(userDao.login(any())).thenReturn(user);
        when(userDao.getUserByUsername(anyString())).thenReturn(user);
        when(jwtUtil.generateToken(any())).thenReturn(expectedToken);
        when(bCryptPasswordEncoder.encode(user.getPassword())).thenReturn("test");
        when(bCryptPasswordEncoder.matches(any(), any())).thenReturn(true);
        String actualToken = userService.login(user);
        
        assertEquals(actualToken, expectedToken);
    }
    
    @Test(expected=LoginException.class)
    public void login_UserNotFound_Error() throws EntityNotFoundException, LoginException {
    	
    	User tempUser = user;
    	tempUser.setUserId(null);

        when(userDao.login(any())).thenReturn(tempUser);
        String token = userService.login(user);
      
        assertEquals(token, null);
        
    }
}