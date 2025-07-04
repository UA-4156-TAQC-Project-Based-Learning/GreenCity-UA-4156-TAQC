package com.greencity.jdbc.dao.eventsDAO;

import com.greencity.jdbc.dao.BaseDAO;
import com.greencity.jdbc.dao.ManagerDAO;
import com.greencity.jdbc.entity.eventsEntity.EventUserLikeEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EventUserLikeDAO extends BaseDAO {

    public EventUserLikeDAO(String login, String password, String url) {
        super(login, password, url);
    }

    public List<EventUserLikeEntity> selectAll() {
        Statement statement = ManagerDAO.getInstance(url, login, password).getStatement();
        List<List<String>> rows;
        try {
            ResultSet rs = statement.executeQuery(EventUserLikeEntity.SELECT_ALL);
            rows = ManagerDAO.parseResultSet(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ManagerDAO.closeAllStatements();
        }

        List<EventUserLikeEntity> likes = new ArrayList<>();
        for (List<String> row : rows) {
            likes.add(EventUserLikeEntity.parseRow(row));
        }
        return likes;
    }

    public List<EventUserLikeEntity> selectByEventId(Long eventId) {
        Statement statement = ManagerDAO.getInstance(url, login, password).getStatement();
        List<List<String>> rows;
        try {
            ResultSet rs = statement.executeQuery(String.format(EventUserLikeEntity.FIND_BY_EVENT_ID, eventId));
            rows = ManagerDAO.parseResultSet(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ManagerDAO.closeAllStatements();
        }

        List<EventUserLikeEntity> likes = new ArrayList<>();
        for (List<String> row : rows) {
            likes.add(EventUserLikeEntity.parseRow(row));
        }
        return likes;
    }

    public List<EventUserLikeEntity> selectByUserId(Long userId) {
        Statement statement = ManagerDAO.getInstance(url, login, password).getStatement();
        List<List<String>> rows;
        try {
            ResultSet rs = statement.executeQuery(String.format(EventUserLikeEntity.FIND_BY_USER_ID, userId));
            rows = ManagerDAO.parseResultSet(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ManagerDAO.closeAllStatements();
        }

        List<EventUserLikeEntity> likes = new ArrayList<>();
        for (List<String> row : rows) {
            likes.add(EventUserLikeEntity.parseRow(row));
        }
        return likes;
    }
}
