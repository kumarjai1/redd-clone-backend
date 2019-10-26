package com.ga.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ga.config.JwtUtil;
import com.ga.dao.UserDao;
import com.ga.dao.UserProfileDao;
import com.ga.entity.Comment;
import com.ga.entity.Post;
import com.ga.entity.User;
import com.ga.entity.UserProfile;
import com.ga.exception.EntityNotFoundException;
import com.ga.exception.LoginException;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

public class UserProfileServiceTest {

	@InjectMocks
	UserProfileServiceImpl userProfileService;
	
	@InjectMocks
	UserProfile userProfile;
	
	@Mock 
	User user;
	
	@Mock
	UserProfileDao userProfileDao;
	
	
	@Before
    public void initMocks() {
      MockitoAnnotations.initMocks(this);
    }
	
//	@Before 
//	public void initializeDummyProfile () {
//		userProfile.setProfileId(1L);
//		userProfile.setAdditionalEmail("additionalemail@testcom");
//		userProfile.setAddress("123 street");
//		userProfile.setMobile("1234562890");
//		user.setUserProfile(userProfile);
//	}

	@Test
	public void createUserProfile_ReturnsUserProfile_Success() {
		userProfile.setProfileId(1L);
		userProfile.setAdditionalEmail("additionalemail@testcom");
		userProfile.setAddress("123 street");
		userProfile.setMobile("1234562890");
		
		when(userProfileDao.createUserProfile(any())).thenReturn(userProfile);
		UserProfile newProfile = userProfileService.createUserProfile(userProfile);
//		Assert.assertNotNull(newProfile);
		Assert.assertEquals(newProfile.getAddress(), userProfile.getAddress());
	}
	
	@Test
	public void getUserProfile_ReturnsProfile_Success() {
		userProfile.setProfileId(1L);
		userProfile.setAdditionalEmail("additionalemail@testcom");
		userProfile.setAddress("123 street");
		userProfile.setMobile("1234562890");
		user.setUserProfile(userProfile);
<<<<<<< HEAD
=======
		
		when(userProfileDao.getProfile()).thenReturn(userProfile);
		UserProfile tempProfile = userProfileService.getUserProfile();
>>>>>>> test-comment-service
		
		when(userProfileDao.getProfile()).thenReturn(userProfile);
		UserProfile tempProfile = userProfileService.getUserProfile();
		Assert.assertNotNull(tempProfile.getAdditionalEmail());
		Assert.assertEquals(tempProfile.getAdditionalEmail(), userProfile.getAdditionalEmail());
	}
}
