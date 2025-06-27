package com.greencity.jdbc.services;

import com.greencity.jdbc.dao.CommentDAO;
import com.greencity.jdbc.dao.UserDAO;
import com.greencity.jdbc.entity.CommentEntity;
import com.greencity.jdbc.entity.UserEntity;

import java.util.List;

public class CommentService extends BaseServise {
    private final CommentDAO commentDAO;
    private final UserDAO userDAO;


    protected CommentService(String login, String password, String url) {
        super(login, password, url);
        commentDAO = new CommentDAO(login, password, url);
        userDAO = new UserDAO(login, password, url);
    }

    public List<CommentEntity> getAllComments() {
        return commentDAO.selectAllComments();
    }

    public List<CommentEntity> getCommentByUserEmail(String email) {
        UserEntity user = userDAO.selectByEmail(email);
        List<CommentEntity> comments = commentDAO.selectAllByUserId(user.getId());
        return comments;
    }
}
