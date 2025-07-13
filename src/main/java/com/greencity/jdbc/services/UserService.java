package com.greencity.jdbc.services;

import com.greencity.jdbc.dao.UserDAO;
import com.greencity.jdbc.entity.UserEntity;

import java.util.List;

public class UserService extends BaseService {
    private final UserDAO userDAO;

    public UserService(String login, String password, String url) {
        super(login, password, url);
        this.userDAO = new UserDAO(login, password, url);
    }

    public List<UserEntity> getAllUsers() {
        return userDAO.selectAll();
    }

    public UserEntity getUserByEmail(String email) {
        return userDAO.selectByEmail(email);
    }

    public int getUserCount() {
        return userDAO.countUsers();
    }
}