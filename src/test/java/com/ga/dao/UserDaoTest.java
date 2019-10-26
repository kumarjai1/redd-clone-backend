package com.ga.dao;

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
import com.ga.exception.EntityNotFoundException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class UserDaoTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();
    
    @InjectMocks
    private User user;
    
    @InjectMocks
    private UserDaoImpl userDao;
     
    @Mock
    private SessionFactory sessionFactory;
    
    @Mock
    private Session session;
    
    @Mock
    Query<User> query;
    
    @Mock
    Transaction transaction;
    
    @Before
    public void initializeDummyUser() {
        user.setUserId(1L);
        user.setEmail("test@test.com");
        user.setPassword("test");
   
    }
    
    @Before
    public void init() {
       when(sessionFactory.getCurrentSession()).thenReturn(session);
       when(session.getTransaction()).thenReturn(transaction);

    }
    
    @Test
    public void signup_User_Success() {
        
        User tempUser = userDao.signup(user);
        assertNotNull(tempUser);
        assertEquals(tempUser.getEmail(), user.getEmail());
    }
    
    @Test
    public void login_User_Success() throws EntityNotFoundException {
    	when(session.createQuery(anyString())).thenReturn(query);
    	when(query.getSingleResult()).thenReturn(user);
        
        User savedUser = userDao.login(user);
        
        assertNotNull("Test returned null object, expected non-null", savedUser);
        assertEquals(savedUser, user);
    }	
     
    @Test(expected=EntityNotFoundException.class)
    public void login_Exception_EntityNotFoundException() throws EntityNotFoundException {
    	when(session.createQuery(anyString())).thenReturn(query);
    	when(query.getSingleResult()).thenThrow(new RuntimeException("test"));
    	userDao.login(user);
    }
    
    @Test
    public void getUserByUsername_User_Success() {
    	when(session.createQuery(anyString())).thenReturn(query);
    	when(query.uniqueResult()).thenReturn(user);
    	
    	User fetchedUser = userDao.getUserByUsername("user1");
    	
    	assertEquals(fetchedUser, user);
    }
    
    @Test
    public void listPosts_Posts_Success(){
      	List<Post> testPosts = new ArrayList();
    	Post post = new Post();
    	post.setPostId(1L);
    	post.setTitle("test");
    	post.setDescription("test");
    	testPosts.add(post);
    	user.setPosts(testPosts);

    	List<Post> fetchedPosts = userDao.listPosts(user);
    	
    	assertEquals(fetchedPosts, testPosts);
    }
    
    @Test
    public void listComments_Commentss_Success(){
    	List<Comment> testComments = new ArrayList();
    	Comment comment = new Comment();
    	comment.setCommentId(1L);
    	comment.setText("test");
    	testComments.add(comment);
    	user.setComments(testComments);
    	    	
    	List<Comment> fetchedComments = userDao.listComments(user);
    	
    	assertEquals(fetchedComments, testComments);
    }
    
}