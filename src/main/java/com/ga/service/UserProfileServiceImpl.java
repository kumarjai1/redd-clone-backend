package com.ga.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ga.dao.UserProfileDao;
import com.ga.entity.UserProfile;

@Service
public class UserProfileServiceImpl implements UserProfileService {
	
	@Autowired
	UserProfileDao userProfileDao;
	
	@Override
	public UserProfile createUserProfile(UserProfile userProfile) {
		return userProfileDao.createUserProfile(userProfile);
	}

	@Override
	public UserProfile getUserProfile() {
		// TODO Auto-generated method stub
		return userProfileDao.getProfile();
	}

}
