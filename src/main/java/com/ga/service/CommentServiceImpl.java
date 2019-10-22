package com.ga.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ga.dao.CommentDao;
import com.ga.entity.Comment;
import com.ga.entity.User;

@Service	
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private CommentDao commentDao;

	@Override
	public Comment createComment(User user, Long postId, Comment comment) {
		return commentDao.createComment(user, postId, comment);
	}
	
	
}
