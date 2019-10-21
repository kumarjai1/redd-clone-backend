package com.ga.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ga.entity.User;

public class UserDaoImpl implements UserDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public User signup(User user) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			session.save(user);
			session.getTransaction().commit();
		} finally {
			session.close();
		}
		return user;
	}

	@Override
	public User login(User user) {
		User savedUser = null;
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			savedUser = (User)session.createQuery("FROM User u WHERE u.username = '" 
			+ user.getUsername() + "'").getSingleResult();	
		} finally {
			session.close();
		}
		// TODO Auto-generated method stub
		return savedUser;
	}

	@Override
	public User getUserByUsername(String username) {
		User savedUser = null; 
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			savedUser = (User)session.createQuery("FROM User u WHERE u.username = '"
			+ username + "'").uniqueResult();
		} finally {
			session.close();
		}
		return savedUser;
	}

}
