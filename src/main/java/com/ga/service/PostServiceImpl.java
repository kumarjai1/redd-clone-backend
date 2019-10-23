package com.ga.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ga.dao.PostDao;
import com.ga.entity.Comment;
import com.ga.entity.Post;
import com.ga.entity.User;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostDao postDao;
	
	@Autowired
	private UserService userService;
	
	@Override
	public Post createPost(Post post) {
		User currentUser = userService.getUser();
		post.setUser(currentUser);
		return postDao.createPost(post);
	}

	@Override
	public List<Post> listPosts() {
		return postDao.listPosts();
	}

	@Override
	public List<Comment> listComments(Long postId) {
		return postDao.listComments(postId);
	}

	@Override
	public String deletePost(Long postId) {
		return postDao.deletePost(postId);
	}

}
