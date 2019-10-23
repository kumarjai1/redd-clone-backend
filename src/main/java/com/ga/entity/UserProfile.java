package com.ga.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="profile")
public class UserProfile {
	
	public UserProfile() {}
	
	@Id
	@Column(name="profile_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long profileId;
	
	@Column(name="additional_email")
	private String additionalEmail;
	
	@Column 
	private String address;
	
	@Column
	private String mobile;

	public Long getProfileId() {
		return profileId;
	}

	public void setProfileId(Long profileId) {
		this.profileId = profileId;
	}

	public String getAdditionalEmail() {
		return additionalEmail;
	}

	public void setAdditionalEmail(String additionalEmail) {
		this.additionalEmail = additionalEmail;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

}
