package com.greencity.jdbc.dao;

import com.greencity.jdbc.entity.EcoNewsEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EcoNewsDAO extends BaseDAO{

    public EcoNewsDAO(String login, String password, String url) {
        super(login, password, url);
    }

    public List<EcoNewsEntity> selectAllNews() {
        Statement statement = ManagerDAO.getInstance(url, login, password).getStatement();
        List<List<String>> rows = null;

        try {
            ResultSet resultSet = statement.executeQuery(EcoNewsEntity.SELECT_ALL);
            rows = ManagerDAO.parseResultSet(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ManagerDAO.closeAllStatements();
        }
        List<EcoNewsEntity> news = new ArrayList<>();
        for (List<String> row : rows){
            news.add(EcoNewsEntity.parseRow(row));
        }
        return news;
    }

    public List<EcoNewsEntity> selectByAuthorId(Integer author_id) {
        Statement statement = ManagerDAO.getInstance(url, login, password).getStatement();
        List<List<String>> rows = null;

        try {
            ResultSet resultSet = statement.executeQuery(String.format(EcoNewsEntity.SELECT_BY_USERID, author_id));
            rows = ManagerDAO.parseResultSet(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ManagerDAO.closeAllStatements();
        }
        List<EcoNewsEntity> news = new ArrayList<>();
        for (List<String> row : rows){
            news.add(EcoNewsEntity.parseRow(row));
        }
        return news;
    }

    public List<EcoNewsEntity> selectByTitle(String title) {
        Statement statement = ManagerDAO.getInstance(url, login, password).getStatement();
        List<List<String>> rows = null;

        try {
            ResultSet resultSet = statement.executeQuery(String.format(EcoNewsEntity.SELECT_BY_TITLE, title));
            rows = ManagerDAO.parseResultSet(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ManagerDAO.closeAllStatements();
        }
        List<EcoNewsEntity> news = new ArrayList<>();
        for(List<String> row : rows){
            news.add(EcoNewsEntity.parseRow(row));
        }
        return news;
    }

    public List<EcoNewsEntity> selectById(Integer id) {
        Statement statement = ManagerDAO.getInstance(url, login, password).getStatement();
        List<List<String>> rows = null;

        try {
            ResultSet resultSet = statement.executeQuery(String.format(EcoNewsEntity.SELECT_BY_ID, id));
            rows = ManagerDAO.parseResultSet(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ManagerDAO.closeAllStatements();
        }
        List<EcoNewsEntity> news = new ArrayList<>();
        for(List<String> row : rows){
            news.add(EcoNewsEntity.parseRow(row));
        }
        return news;
    }

    public void deleteTagsById(Integer id) {
        Statement statement = ManagerDAO.getInstance(url, login, password).getStatement();

        try {
            statement.executeUpdate(String.format(EcoNewsEntity.DELETE_TAG_BY_ID, id));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ManagerDAO.closeAllStatements();
        }
    }
    }

