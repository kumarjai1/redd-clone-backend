package com.ga.service;

import com.ga.entity.Comment;
import com.ga.entity.User;

public interface CommentService {

	public Comment createComment(User user, Long postId, Comment comment);
}
