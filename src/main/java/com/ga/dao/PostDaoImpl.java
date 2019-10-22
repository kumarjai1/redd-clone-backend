package com.ga.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ga.entity.Post;
import com.ga.entity.User;

@Repository
public class PostDaoImpl implements PostDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public Post createPost(String username, Post post) {
		User currentUser = userDao.getUserByUsername(username);
		Session session = sessionFactory.getCurrentSession();
		
		try {
			session.beginTransaction();
			post.setUser(currentUser);
			session.save(post);
			session.getTransaction().commit();
		} finally {
			session.close();
		}
		return post;
	}

	@Override
	public List<Post> listPosts() {
		Session session = sessionFactory.getCurrentSession();
		List <Post> allPosts = null;
		try {
			session.beginTransaction();
			allPosts = session.createQuery("From Post").getResultList();
			
		} finally {
			session.close();
		}
		return allPosts;
 	}

}
