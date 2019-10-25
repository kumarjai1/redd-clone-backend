package com.ga.dao;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import com.ga.entity.User;
import com.ga.exception.EntityNotFoundException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class UserDaoTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();
    
    @InjectMocks
    private User user;
    
    @InjectMocks
    private UserDaoImpl userDao;
     
    @Mock
    private SessionFactory sessionFactory;
    
    @Mock
    private Session session;
    
    @Mock
    Query<User> query;
    
    @Mock
    Transaction transaction;
    
    @Before
    public void initializeDummyUser() {
        user.setUserId(1L);
        user.setEmail("test@test.com");
        user.setPassword("test");
   
    }
    
    @Before
    public void init() {
       when(sessionFactory.getCurrentSession()).thenReturn(session);
       when(session.getTransaction()).thenReturn(transaction);

    }
    
    @Test
    public void signup_User_Success() {
        
        User tempUser = userDao.signup(user);
        assertNotNull(tempUser);
        assertEquals(tempUser.getEmail(), user.getEmail());
    }
    
    @Test
    public void login_User_Success() throws EntityNotFoundException {
    	when(session.createQuery(anyString())).thenReturn(query);
    	when(query.getSingleResult()).thenReturn(user);
        
        User savedUser = userDao.login(user);
        
        assertNotNull("Test returned null object, expected non-null", savedUser);
        assertEquals(savedUser, user);
    }	
    
    
}