package com.ga.dao;

import com.ga.entity.User;
import com.ga.entity.UserProfile;

public interface UserProfileDao {
	public UserProfile createUserProfile (UserProfile userProfile);
	public UserProfile getProfile ();
	
}
