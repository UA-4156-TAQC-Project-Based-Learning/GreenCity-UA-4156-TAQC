package com.greencity.jdbc.dao;

import com.greencity.jdbc.entity.TagEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TagDAO extends BaseDAO {

    public TagDAO(String login, String password, String url) {
        super(login, password, url);
    }

    public List<TagEntity> selectAll() {
        Statement statement = ManagerDAO.getInstance(url, login, password).getStatement();
        List<List<String>> rows;
        try {
            ResultSet resultSet = statement.executeQuery(TagEntity.SELECT_ALL);
            rows = ManagerDAO.parseResultSet(resultSet);
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ManagerDAO.closeAllStatements();
        }
        List<TagEntity> tags = new ArrayList<>();
        for (List<String> row : rows) {
            tags.add(TagEntity.parseRow(row));
        }
        return tags;
    }

    public List<TagEntity> selectByType(String type){
        Statement statement = ManagerDAO.getInstance(url, login , password).getStatement();
        List<List<String>> rows;
        try {
            String query = String.format(TagEntity.FIND_BY_TYPE, type);
            ResultSet resultSet = statement.executeQuery(query);
            rows = ManagerDAO.parseResultSet(resultSet);
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ManagerDAO.closeAllStatements();
        }
        List<TagEntity> tags = new ArrayList<>();
        for (List<String> row : rows) {
            tags.add(TagEntity.parseRow(row));
        }
        return tags;
    }
}
