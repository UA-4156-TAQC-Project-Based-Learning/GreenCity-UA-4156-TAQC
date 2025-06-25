package com.greencity.jdbc.dao;

import com.greencity.jdbc.entity.CommentEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CommentDAO {
    private final String login;
    private final String password;
    private final String url;

    public CommentDAO(String login, String password, String url) {
        this.login = login;
        this.password = password;
        this.url = url;
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
        for(List<String> row: rows){
            comments.add(CommentEntity.parseRow(row));
        }
        return comments;

    }
}
