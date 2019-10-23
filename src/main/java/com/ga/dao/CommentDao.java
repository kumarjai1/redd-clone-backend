package com.ga.dao;

import com.ga.entity.Comment;
import com.ga.entity.User;

public interface CommentDao {
	public Comment createComment(User user, Long postId, Comment comment);
	public String deleteComment(Long commentId);
}
