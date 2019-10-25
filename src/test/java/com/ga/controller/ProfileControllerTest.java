package com.ga.controller;

import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import com.ga.entity.Comment;
import com.ga.entity.User;
import com.ga.entity.UserProfile;
import com.ga.service.CommentService;
import com.ga.service.UserProfileService;
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
public class ProfileControllerTest {

    private MockMvc mockMvc;
       
    @Mock 
    private UserService userService;
    
    @Mock 
    private UserProfileService userProfileService;
    
    @InjectMocks
    private ProfileController profileController;
    
    @Before
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(profileController).build();
    }
    
    @Test
 	public void createProfile_Profile_Success() throws Exception {
    	UserProfile userProfile = new UserProfile();
//    	userProfile.setProfileId(1L);
    	userProfile.setAdditionalEmail("test@test.com");
    	userProfile.setAddress("test");
    	userProfile.setMobile("111-111-111");
   
    	when(userProfileService.createUserProfile(any())).thenReturn(userProfile);
    	
    	RequestBuilder requestBuilder = MockMvcRequestBuilders
			        .post("/profile")
    				.contentType(MediaType.APPLICATION_JSON)
    				.content(createProfileInJson("test@test.com", "test", "111-111-111"));
    				
    	MvcResult result = mockMvc.perform(requestBuilder)
				.andExpect(status().isOk())
				.andExpect(content().json("{\"additionalEmail\":\"test@test.com\", \"address\":\"test\",\"mobile\":\"111-111-111\"}"))
	            .andReturn();
	
    	System.out.println(result.getResponse().getContentAsString());	
 	}
    
    private String createProfileInJson(String email, String address, String mobile) {
    	 return "{ \"additionalEmail\": \"" + email + "\", " +
    			 "\"address\": \"" + address + "\", " +	
                 "\"mobile\":\"" + mobile + "\"}";
    }

    @Test
    public void getUserProfile_Profile_Success() throws Exception {
    	UserProfile userProfile = new UserProfile();
    	userProfile.setAdditionalEmail("test@test.com");
    	userProfile.setAddress("test");
    	userProfile.setMobile("111-111-111");
    	
    	when(userProfileService.getUserProfile()).thenReturn(userProfile);
    	
    	RequestBuilder requestBuilder = MockMvcRequestBuilders
			        .get("/profile");
    			
    	MvcResult result = mockMvc.perform(requestBuilder)
				.andExpect(status().isOk())
				.andExpect(content().json("{\"additionalEmail\":\"test@test.com\", \"address\":\"test\",\"mobile\":\"111-111-111\"}"))
	            .andReturn();
	
    	System.out.println(result.getResponse().getContentAsString());	
    	
    }
//    public void deleteComment_Comment_Success() throws Exception {
//    	when(commentService.deleteComment(anyLong())).thenReturn("Comment was deleted");
//    	
//    	RequestBuilder requestBuilder = MockMvcRequestBuilders
//			       .delete("/comment/1");
//    	
//    	MvcResult result = mockMvc.perform(requestBuilder)
//				.andExpect(status().isOk())
//				.andExpect(content().string("Comment was deleted"))
//	            .andReturn();
//    	
//    	System.out.println(result.getResponse().getContentAsString());	
//    }
  
}