package com.greencity.jdbc.dao;

import com.greencity.jdbc.entity.UserEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends BaseDAO {
    public UserDAO(String login, String password, String url) {
        super(login, password, url);
    }

    public List<UserEntity> selectAll() {
        Statement statement = ManagerDAO.getInstance(url, login, password).getStatement();
        List<List<String>> rows = null;
        try {
            ResultSet resultSet = statement.executeQuery(UserEntity.SELECT_ALL);
            rows = ManagerDAO.parseResultSet(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ManagerDAO.closeAllStatements();
        }
        List<UserEntity> comments = new ArrayList<>();
        for (List<String> row : rows) {
            comments.add(UserEntity.parseRow(row));
        }
        return comments;

    }

    public UserEntity selectByEmail(String email) {
        Statement statement = ManagerDAO.getInstance(url, login, password).getStatement();
        List<List<String>> rows = null;
        try {
            ResultSet resultSet = statement.executeQuery(String.format(UserEntity.FIND_BY_EMAIL, email));
            rows = ManagerDAO.parseResultSet(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ManagerDAO.closeAllStatements();
        }
        return UserEntity.parseRow(rows.get(0));
    }

    public Integer countUsers() {
        Integer count = 0;
        Statement statement = ManagerDAO.getInstance(url, login, password).getStatement();
        ResultSet resultSet = null;

        try {
            resultSet = statement.executeQuery(UserEntity.COUNT_USERS);
            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ManagerDAO.closeAllStatements();
        }

        return count;
    }
}
