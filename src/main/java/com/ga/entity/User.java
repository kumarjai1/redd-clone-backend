package com.ga.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="users")
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    
//    @NotBlank(message = "Username cannot be blank")
    @Column(unique = true, nullable = false)
    private String username;
    
    @NotBlank(message = "Password cannot be blank")
    @Column(name = "password", nullable = false)
    private String password;
    
    @NotBlank(message = "Email cannot be blank")    
    @Column(name = "email", unique = true, nullable = false)
    @Email(message = "User email is invalid")
    private String email;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_profile_id")
    @JsonIgnore
    private UserProfile userProfile;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy= "user", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH })
    private List<Post> posts;
    
    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy= "user", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH })
    private List<Comment> comments;
    
    public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public User() {}
    
    public Long getUserId() {
		return userId;
    }

    public void setUserId(Long userId) {
		this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
		return password;
    }

    public void setPassword(String password) {
		this.password = password;
    }	
}
