package com.greencity.jdbc.dao;

import com.greencity.jdbc.entity.CommentEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CommentDAO extends BaseDAO {


    public CommentDAO(String login, String password, String url) {
        super(login, password, url);
    }

    public List<CommentEntity> selectAllComments() {
        Statement statement = ManagerDAO.getInstance(url, login, password).getStatement();
        List<List<String>> rows = null;
        try {
            ResultSet resultSet = statement.executeQuery(CommentEntity.SELECT_ALL);
            rows = ManagerDAO.parseResultSet(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ManagerDAO.closeAllStatements();
        }
        List<CommentEntity> comments = new ArrayList<>();
        for (List<String> row : rows) {
            comments.add(CommentEntity.parseRow(row));
        }
        return comments;

    }

    public List<CommentEntity> selectAllByUserId(Integer user_id) {
        Statement statement = ManagerDAO.getInstance(url, login, password).getStatement();
        List<List<String>> rows = null;
        try {
            ResultSet resultSet = statement.executeQuery(String.format(CommentEntity.SELECT_BY_USER_ID, user_id));
            rows = ManagerDAO.parseResultSet(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ManagerDAO.closeAllStatements();
        }
        List<CommentEntity> comments = new ArrayList<>();
        for (List<String> row : rows) {
            comments.add(CommentEntity.parseRow(row));
        }
        return comments;

    }
}
