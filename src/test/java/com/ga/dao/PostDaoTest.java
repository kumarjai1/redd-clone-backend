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

import static org.mockito.ArgumentMatchers.any;

public class PostDaoTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();
    
    @InjectMocks
    private PostDaoImpl postDao;
     
    @Mock
    private SessionFactory sessionFactory;
    
    @Mock
    private Session session;
    
    @Mock
    Query<Post> query;
    
    @Mock
    Query<Comment> queryComment;
    
    @Mock
    Transaction transaction;
    
    @InjectMocks
    private Post post;
    
    @Before
    public void initializePost() {
        post.setPostId(1L);
        post.setTitle("test");
        post.setDescription("test");
    }
    
    @Before
    public void init() {
       when(sessionFactory.getCurrentSession()).thenReturn(session);
       when(session.getTransaction()).thenReturn(transaction);

    }
    
    @Test
    public void createPost_Post_Success() {
        Post testPost = postDao.createPost(post);
        assertEquals(testPost.getTitle(), post.getTitle());
    }
    
    @Test
    public void listPosts_Post_Success() {
      	List<Post> testPosts = new ArrayList();
    	Post post = new Post();
    	post.setPostId(1L);
    	post.setTitle("test");
    	post.setDescription("test");
    	testPosts.add(post);
    	
    	when(session.createQuery(anyString())).thenReturn(query);
    	when(query.getResultList()).thenReturn(testPosts);
    	
    	List<Post> fetchedPosts = postDao.listPosts();
    	
    	assertEquals(fetchedPosts, testPosts);
    	
    }
    
    @Test
    public void listComments_Comments_Success() {
       	List<Comment> testComments = new ArrayList();
    	Comment comment = new Comment();
    	comment.setCommentId(1L);
    	comment.setText("test");
    	testComments.add(comment);
    	
    	when(session.createQuery(anyString())).thenReturn(queryComment);
    	when(queryComment.getResultList()).thenReturn(testComments);
    	
    	List<Post> fetchedComments = postDao.listPosts();
    	
    	assertEquals(fetchedComments, testComments);
    }
    
    @Test
    public void deletePost_Post_Success() {
    	String results = postDao.deletePost(1L);
    	assertEquals(results, "Post was successfully deleted");
    }
}