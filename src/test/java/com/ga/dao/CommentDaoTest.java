package com.ga.dao;

import static org.mockito.Mockito.when;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import com.ga.entity.Comment;
import com.ga.entity.Post;
import com.ga.entity.User;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class CommentDaoTest {
	
	@Rule 
	public MockitoRule rule = MockitoJUnit.rule();
	
	@InjectMocks 
	private CommentDaoImpl commentDao;
	
	@Mock 
	private SessionFactory sessionFactory;
	
	@Mock
	private Session session;
	
	@Mock
	Query<Comment> query;
	
	@Mock 
	Query<Post> queryPost;
	
	@Mock 
	Transaction transaction;
	
	@InjectMocks 
	private Comment comment;
	
	@InjectMocks 
	private Post post;
	
	@Mock 
	private User user;
	
	@Before
	public void initializeComment () {
		post.setPostId(1L);
        post.setTitle("test");
        post.setDescription("test");
        comment.setCommentId(1L);
        comment.setText("test comment");
        comment.setUser(user);
	}
	
	@Before
	public void init () {
		when(sessionFactory.getCurrentSession()).thenReturn(session);
		when(session.getTransaction()).thenReturn(transaction);

	}
	
	@Test 
	public void createComment_ReturnsComment_Success() {
		Comment testComment = commentDao.createComment(user, post.getPostId(), comment);
		assertNotNull(testComment);
		assertEquals(testComment.getText(), comment.getText());
	}
	
	@Test
	public void deleteComment_ReturnDeleteMsg_Success() {
		String result = commentDao.deleteComment(comment.getCommentId());
		System.out.println(result);
		assertNotNull(result);
		assertEquals(result, "Comment was successfully deleted");
	}

}
