package com.ga.dao;

import static org.mockito.Mockito.when;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import com.ga.entity.User;
import com.ga.entity.UserProfile;
import com.ga.service.UserServiceImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class UserProfileDaoTest {
	
	@Rule 
	public MockitoRule rule = MockitoJUnit.rule();
	
	@InjectMocks 
	private UserProfileDaoImpl userProfileDao;
	
	@InjectMocks 
	private UserProfile userProfile;
	
	@Mock 
	private SessionFactory sessionFactory;
	
	@Mock 
	private Session session;
	
//	@Mock 
//	Query <UserProfile> query;
	
	@Mock 
	Transaction transaction;
	
	@Mock 
	UserServiceImpl userService;
	
	@Mock
	private User user;
	
	@Before
	public void initializeProfile() {
		userProfile.setProfileId(1L);
		userProfile.setAdditionalEmail("test@gmail.com");
		userProfile.setMobile("1234567890");
		userProfile.setAddress("123 street");
//		user.setUserProfile(userProfile);
	}
	
	@Before 
	public void init () {
		when(sessionFactory.getCurrentSession()).thenReturn(session);
		when(session.getTransaction()).thenReturn(transaction);
	}
	
	@Test 
	public void createUserProfile_ReturnUserProfile_Success() {
		when(userService.getUser()).thenReturn(user);
		UserProfile tempUserProfile = userProfileDao.createUserProfile(userProfile);
		
		assertNotNull(tempUserProfile);
		assertEquals(tempUserProfile.getAdditionalEmail(), userProfile.getAdditionalEmail());
	}
}
