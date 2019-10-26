package com.ga.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ga.dao.CommentDao;
import com.ga.dao.PostDao;
import com.ga.dao.UserDao;
import com.ga.entity.Comment;
import com.ga.entity.Post;
import com.ga.entity.User;
import com.ga.exception.EntityNotFoundException;
import com.ga.exception.LoginException;


import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

public class CommentServiceTest {
	
	@InjectMocks 
	CommentServiceImpl commentService;
	
	@InjectMocks
	private Comment comment;
	
	@Mock
	CommentDao commentDao;
	
	@InjectMocks
	private User user;
	
	@InjectMocks 
	private Post post;
	
	@Before
    public void initMocks() {
      MockitoAnnotations.initMocks(this);
    }
	
	@Before
    public void initializeDummyUser() {
		user.setUserId(1L);
        user.setUsername("test");
        user.setEmail("test@test.com");
        user.setPassword("test");
		post.setPostId(1L);
		post.setTitle("title");
		post.setDescription("title description");
		post.setPostId(1L);
		post.setUser(user);
		comment.setCommentId(1L);
		comment.setPost(post);
		comment.setText("test comment");
		comment.setUser(user);
    }
	
	
	@Test
	public void createComment_ReturnsComment_Success() {
		
		when(commentDao.createComment(any(), any(), any())).thenReturn(comment);
		Comment tempComment = commentService.createComment(this.user, 1L, comment);
		
		assertEquals(comment.getText(), tempComment.getText());
		
	}
	
	@Test 
	public void deleteComment_ReturnsDeletedCommentMsg_Success() {
		String msg = "Post Successfuly Deleted";
		when (commentDao.deleteComment(any())).thenReturn(msg);
		String msg2 = commentService.deleteComment(1L);
		
		assertEquals(msg, msg2);
	}
	
}
