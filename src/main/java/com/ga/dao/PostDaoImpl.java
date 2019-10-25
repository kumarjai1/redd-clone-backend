package com.ga.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ga.entity.Comment;
import com.ga.entity.Post;
import com.ga.entity.User;

@Repository
public class PostDaoImpl implements PostDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Post createPost(Post post) {
		Session session = sessionFactory.getCurrentSession();
		
		try {
			session.beginTransaction();
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
	
	@Override
	public List<Comment> listComments(Long postId) {
		Session session = sessionFactory.getCurrentSession();
		Post currentPost = null;
		List <Comment> comments = null;
		try {
			session.beginTransaction();
			
			comments = session.createQuery("FROM Comment c WHERE c.post.postId = " + postId).getResultList();
//			currentPost = session.get(Post.class, postId);
//			comments = currentPost.getComments();
			
			session.getTransaction().commit();
		} finally {
			session.close();
		}
		return comments;
	}

	@Override
	public String deletePost(Long postId) {
		Session session = sessionFactory.getCurrentSession();
		Post currentPost = null;
		String results = "";
		try {
			session.beginTransaction();
			currentPost = session.get(Post.class, postId);
			session.delete(currentPost);
			session.getTransaction().commit();
			results = "Post was successfully deleted";
		} finally {
			session.close();
		}
		
		return results;
	}

}
