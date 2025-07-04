package com.greencity.jdbc.dao.eventsDAO;

import com.greencity.jdbc.dao.BaseDAO;
import com.greencity.jdbc.dao.ManagerDAO;
import com.greencity.jdbc.entity.eventsEntity.EventUserDislikeEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EventUserDislikeDAO extends BaseDAO {

    public EventUserDislikeDAO(String login, String password, String url) {
        super(login, password, url);
    }

    public List<EventUserDislikeEntity> selectAll() {
        Statement statement = ManagerDAO.getInstance(url, login, password).getStatement();
        List<List<String>> rows;
        try {
            ResultSet rs = statement.executeQuery(EventUserDislikeEntity.SELECT_ALL);
            rows = ManagerDAO.parseResultSet(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ManagerDAO.closeAllStatements();
        }

        List<EventUserDislikeEntity> dislikes = new ArrayList<>();
        for (List<String> row : rows) {
            dislikes.add(EventUserDislikeEntity.parseRow(row));
        }
        return dislikes;
    }

    public List<EventUserDislikeEntity> selectByEventId(Long eventId) {
        Statement statement = ManagerDAO.getInstance(url, login, password).getStatement();
        List<List<String>> rows;
        try {
            ResultSet rs = statement.executeQuery(String.format(EventUserDislikeEntity.FIND_BY_EVENT_ID, eventId));
            rows = ManagerDAO.parseResultSet(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ManagerDAO.closeAllStatements();
        }

        List<EventUserDislikeEntity> dislikes = new ArrayList<>();
        for (List<String> row : rows) {
            dislikes.add(EventUserDislikeEntity.parseRow(row));
        }
        return dislikes;
    }

    public List<EventUserDislikeEntity> selectByUserId(Long userId) {
        Statement statement = ManagerDAO.getInstance(url, login, password).getStatement();
        List<List<String>> rows;
        try {
            ResultSet rs = statement.executeQuery(String.format(EventUserDislikeEntity.FIND_BY_USER_ID, userId));
            rows = ManagerDAO.parseResultSet(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ManagerDAO.closeAllStatements();
        }

        List<EventUserDislikeEntity> dislikes = new ArrayList<>();
        for (List<String> row : rows) {
            dislikes.add(EventUserDislikeEntity.parseRow(row));
        }
        return dislikes;
    }

}
