package com.ga.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ga.entity.UserProfile;
import com.ga.service.UserProfileService;

@RestController
@RequestMapping("/profile")
public class ProfileController {
	
	private UserProfileService userProfileService;
	
	@Autowired 
	public void setUserProfileService (UserProfileService userProfileService) {
		this.userProfileService = userProfileService;
	}
	
	@PostMapping
	public UserProfile createUserProfile(@RequestBody UserProfile userProfile) { 
			return userProfileService.createUserProfile(userProfile);
				
	}
	
	@GetMapping
	public UserProfile getUserProfile() {
		UserProfile userProfile = userProfileService.getUserProfile();
		if (userProfile.getAdditionalEmail() == null && userProfile.getMobile() == null && userProfile.getAddress() == null) {
			userProfile = new UserProfile();
		}
		return userProfile;
	}

}
