package com.ga.controller;

import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.ga.entity.Comment;
import com.ga.entity.Post;
import com.ga.entity.User;
import com.ga.service.UserService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.springframework.test.web.servlet.MvcResult;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    private MockMvc mockMvc;
    
    @Mock
    private UserService userService;
    
    @InjectMocks
    private UserController userController;
    
    @Before
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }
    
    @Test
 	public void signup_User_Success() throws Exception {
    	User testUser = new User();
    	testUser.setEmail("test@test.com");
    	testUser.setPassword("test");
    	testUser.setUsername("test");
    			
 		RequestBuilder requestBuilder = MockMvcRequestBuilders
 			       .post("/user/signup")
 			       .contentType(MediaType.APPLICATION_JSON)
 			       .content(createUserInJson("test","test"));
 		
 		when(userService.signup(any())).thenReturn("123456");
 		when(userService.getUser()).thenReturn(testUser);
 		
 		MvcResult result = mockMvc.perform(requestBuilder)
 	              .andExpect(status().isOk())
 	              .andExpect(content().json("{\"token\":\"123456\", \"username\":\"test\"}"))
 	              .andReturn();
 	      
 	      System.out.println(result.getResponse().getContentAsString());
 	      
 	}
    
    @Test
    public void login_User_Success() throws Exception {
    	User testUser = new User();
    	testUser.setEmail("test@test.com");
    	testUser.setPassword("test");
    	testUser.setUsername("test");
    	
    	RequestBuilder requestBuilder = MockMvcRequestBuilders
			       .post("/user/login")
			       .contentType(MediaType.APPLICATION_JSON)
			       .content(createUserInJson("test","test"));
		
		when(userService.login(any())).thenReturn("123456");
		when(userService.getUser()).thenReturn(testUser);
		
		MvcResult result = mockMvc.perform(requestBuilder)
	              .andExpect(status().isOk())
	              .andExpect(content().json("{\"token\":\"123456\", \"username\":\"test\"}"))
	              .andReturn();
	      
	      System.out.println(result.getResponse().getContentAsString());
    }
    
    @Test
    public void listPosts_User_Success() throws Exception {
    	List<Post> testPosts = new ArrayList();
    	Post post = new Post();
    	post.setPostId(1L);
    	post.setTitle("test");
    	post.setDescription("test");
    	testPosts.add(post);
    	
    	RequestBuilder requestBuilder = MockMvcRequestBuilders
			       .get("/user/post");
			   
    	when(userService.listPosts()).thenReturn(testPosts);
    	
    	MvcResult result = mockMvc.perform(requestBuilder)
    				.andExpect(status().isOk())
		            .andExpect(content().json("[{\"postId\":1, \"title\":\"test\",\"description\":\"test\",\"user\":null}]"))
		            .andReturn();
    	
    	System.out.println(result.getResponse().getContentAsString());
    }
    @Test
    public void listComments_User_Success() throws Exception {
    	List<Comment> testComments = new ArrayList();
    	Comment comment = new Comment();
    	comment.setCommentId(1L);
    	comment.setText("test");
    	
    	testComments.add(comment);
    	
    	RequestBuilder requestBuilder = MockMvcRequestBuilders
			       .get("/user/comment");
			   
    	when(userService.listComments()).thenReturn(testComments);
    	
    	MvcResult result = mockMvc.perform(requestBuilder)
    				.andExpect(status().isOk())
		            .andExpect(content().json("[{\"commentId\":1, \"text\":\"test\",\"user\":null}]"))
		            .andReturn();
    	
    	System.out.println(result.getResponse().getContentAsString());
    }
    
    private static String createUserInJson(String username, String password) {
        return "{ \"username\": \"" + username + "\", " +
                "\"password\":\"" + password + "\"}";
    }
    
}