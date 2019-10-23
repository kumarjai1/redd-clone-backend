package com.ga.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ga.entity.Comment;
import com.ga.entity.Post;
import com.ga.entity.User;

@Repository
public class CommentDaoImpl implements CommentDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Comment createComment(User user, Long postId, Comment comment) {
		Session session = sessionFactory.getCurrentSession();
		Post post = null;
		
		try {
			session.beginTransaction();
			post = session.get(Post.class, postId);
			comment.setUser(user);
			comment.setPost(post);
			session.save(comment);
			session.getTransaction().commit();
		} finally {
			session.close();
		}
		return comment;
	}

	@Override
	public String deleteComment(Long commentId) {
		Session session = sessionFactory.getCurrentSession();
		Comment currentComment = null;
		String results = "";
		try {
			session.beginTransaction();
			currentComment = session.get(Comment.class, commentId);
			session.delete(currentComment);
			
			session.getTransaction().commit();
			results = "Comment was successfully deleted";
		} finally {
			session.close();
		}
		
		return results;
	}
}
