package com.ga.entity;

import javax.persistence.*;

@Entity
@Table(name = "posts")
public class Post {
	@Id
    @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;
    
    @Column(unique = true, nullable = false)
    private String title;
	
    @Column
    private String description;
}
