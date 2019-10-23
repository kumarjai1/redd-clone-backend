package com.ga.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ga.entity.User;
import com.ga.entity.UserProfile;
import com.ga.service.UserService;

@Repository
public class UserProfileDaoImpl implements UserProfileDao{
	
	@Autowired
	UserService userService;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public UserProfile createUserProfile(UserProfile userProfile) {
		User currentUser = userService.getUser();
		Session session = sessionFactory.getCurrentSession();
		
		try {
			session.beginTransaction();
			session.save(userProfile);
			currentUser.setUserProfile(userProfile);
			session.update(currentUser);
			session.getTransaction().commit();
		} finally {
			session.close();
		}
		return userProfile;
	}

	@Override
	public UserProfile getProfile() {
		User currentUser = userService.getUser(); 
		// TODO Auto-generated method stub
		return currentUser.getUserProfile();
	}
	
}
