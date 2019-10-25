package com.ga.controller;

import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ga.entity.Comment;
import com.ga.entity.Post;
import com.ga.entity.User;
import com.ga.service.PostService;
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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PostControllerTest {

    private MockMvc mockMvc;
    
    @Mock
    private PostService postService;
    
    @InjectMocks
    private PostController postController;
    
    @Before
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(postController).build();
    }
    
    @Test
 	public void listPosts_Post_Success() throws Exception {
    	List<Post> testPosts = new ArrayList();
    	Post post = new Post();
    	post.setPostId(1L);
    	post.setTitle("test");
    	post.setDescription("test");
    	testPosts.add(post);
    	
    	when(postService.listPosts()).thenReturn(testPosts);
    	
    	RequestBuilder requestBuilder = MockMvcRequestBuilders
			       .get("/post/list");
    	
    	MvcResult result = mockMvc.perform(requestBuilder)
				.andExpect(status().isOk())
	            .andExpect(content().json("[{\"postId\":1, \"title\":\"test\",\"description\":\"test\",\"user\":null}]"))
	            .andReturn();
	
    	System.out.println(result.getResponse().getContentAsString());	
 	}
    
    
//	@GetMapping("{postId}/comment")
//	public List<Comment> getComments(@PathVariable Long postId) {
//		return postService.listComments(postId);
//	}
    @Test
 	public void getComments_Post_Success() throws Exception {
    	List<Comment> testComments = new ArrayList();
    	Comment comment = new Comment();
    	comment.setCommentId(1L);
    	comment.setText("test");
    	testComments.add(comment);
    	
    	when(postService.listComments(anyLong())).thenReturn(testComments);
    	
    	RequestBuilder requestBuilder = MockMvcRequestBuilders
			       .get("/post/1/comment");
    	
    	MvcResult result = mockMvc.perform(requestBuilder)
				.andExpect(status().isOk())
				.andExpect(content().json("[{\"commentId\":1, \"text\":\"test\",\"user\":null}]"))
	            .andReturn();
	
    	System.out.println(result.getResponse().getContentAsString());	
 	}
    
    @Test
    public void deletePost_Post_Success() throws Exception {
    	when(postService.deletePost(anyLong())).thenReturn("Post was deleted");
    	
    	RequestBuilder requestBuilder = MockMvcRequestBuilders
			       .delete("/post/1");
    	
    	MvcResult result = mockMvc.perform(requestBuilder)
				.andExpect(status().isOk())
				.andExpect(content().string("Post was deleted"))
	            .andReturn();
    	
    	System.out.println(result.getResponse().getContentAsString());	
    }
    
}