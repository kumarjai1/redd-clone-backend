package com.ga.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ga.entity.Post;
import com.ga.entity.User;
import com.ga.exception.EntityNotFoundException;

@Repository
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
	public User login(User user) throws EntityNotFoundException {
		User savedUser = null;
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			savedUser = (User)session.createQuery("FROM User u WHERE u.email = '" 
			+ user.getEmail() + "'").getSingleResult();	
		} catch (Exception es) {
			throw new EntityNotFoundException("User does not exist");
		}	
			finally {
		}
			session.close();
		
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

	@Override
	public List<Post> listPosts(User user) {
		Session session = sessionFactory.getCurrentSession();
		List <Post> posts = null;
		try {
			session.beginTransaction();
			posts = user.getPosts();
			session.getTransaction().commit();
		} finally {
			session.close();
		}
		return posts;
	}

}
