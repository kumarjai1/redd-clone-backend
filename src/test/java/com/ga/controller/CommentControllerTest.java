package com.ga.controller;

import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import com.ga.entity.Comment;
import com.ga.entity.User;
import com.ga.service.CommentService;
import com.ga.service.UserService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.test.web.servlet.MvcResult;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CommentControllerTest {

    private MockMvc mockMvc;
    
    @Mock
    private CommentService commentService;
    
    @Mock 
    private UserService userService;
    
    @InjectMocks
    private CommentController commentController;
    
    @Before
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(commentController).build();
    }
    
    @Test
 	public void createComment_Comment_Success() throws Exception {
    	Comment comment = new Comment();
    	comment.setCommentId(1L);
    	comment.setText("test");
    	
    	User testUser = new User();
    	
    	when(userService.getUser()).thenReturn(testUser);
    	when(commentService.createComment(any(), anyLong(), any())).thenReturn(comment);
    	
    	RequestBuilder requestBuilder = MockMvcRequestBuilders
			        .post("/comment/{postId}", "1")
    				.contentType(MediaType.APPLICATION_JSON)
    				.content(createCommentInJson("test"));
    				
    	MvcResult result = mockMvc.perform(requestBuilder)
				.andExpect(status().isOk())
				.andExpect(content().json("{\"commentId\":1, \"text\":\"test\",\"user\":null}"))
	            .andReturn();
	
    	System.out.println(result.getResponse().getContentAsString());	
 	}
    
    private String createCommentInJson(String text) {
    	return "{ \"text\": \"" + text + "\"}";
    }

    @Test
    public void deleteComment_Comment_Success() throws Exception {
    	when(commentService.deleteComment(anyLong())).thenReturn("Comment was deleted");
    	
    	RequestBuilder requestBuilder = MockMvcRequestBuilders
			       .delete("/comment/1");
    	
    	MvcResult result = mockMvc.perform(requestBuilder)
				.andExpect(status().isOk())
				.andExpect(content().string("Comment was deleted"))
	            .andReturn();
    	
    	System.out.println(result.getResponse().getContentAsString());	
    }
  
}