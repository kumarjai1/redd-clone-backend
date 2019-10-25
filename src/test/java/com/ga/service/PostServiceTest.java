package com.ga.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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

public class PostServiceTest {
	
	@InjectMocks 
	PostServiceImpl postService;
	
	@InjectMocks
	Post post;
	
	@InjectMocks
	User user;
	
	@InjectMocks
	Comment comment;
	
	@Mock
	UserService userService;
	
	@Mock
	PostDao postDao;
	

	
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
    }
	
	@Before
	public void initializeDummyPost() {
		post.setPostId(1L);
		post.setTitle("title");
		post.setDescription("title description");
		post.setPostId(1L);
		post.setUser(user);
	}
	
	@Before 
	public void initializeDummyComment () {
		comment.setText("test comment");
		comment.setPost(post);
	}
	
	@Test
	public void createPost_ReturnPost_Success() {
		
		when(postDao.createPost(any())).thenReturn(post);
		
		Post tempPost = postService.createPost(post);
		
		assertNotNull(tempPost.getTitle());
		assertEquals(tempPost.getTitle(), post.getTitle());
	}
	
	@Test 
	public void ListPosts_ReturnsPostLists_Success() {
		List<Post> posts = new ArrayList();
		posts.add(post);
	
		when(postDao.listPosts()).thenReturn(posts);
		List<Post> tempPosts = postService.listPosts();
		tempPosts.add(post);
		
		assertEquals(tempPosts.get(0).getTitle(), posts.get(0).getTitle());
	}
	
	@Test 
	public void deletePost_ReturnsDeletedPostMsg_Success() {
		String msg = "Post Successfuly Deleted";
		when(postDao.deletePost(any())).thenReturn(msg);
		String msg2 = postService.deletePost(1L);
		
		assertEquals(msg, msg2);
	}
	
	@Test
	public void listComments_ReturnCommentsOnPost_Success(){
		List<Comment> comments = new ArrayList();
		comments.add(comment);
		
		when(postDao.listComments(any())).thenReturn(comments);
		
		List<Comment> tempComments = postService.listComments(1L);
		
		assertEquals(tempComments.get(0).getText(), comments.get(0).getText());
	}
}
