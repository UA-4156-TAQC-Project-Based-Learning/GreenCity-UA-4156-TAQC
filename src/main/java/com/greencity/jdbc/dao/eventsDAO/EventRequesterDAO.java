package com.greencity.jdbc.dao.eventsDAO;

import com.greencity.jdbc.dao.BaseDAO;
import com.greencity.jdbc.dao.ManagerDAO;
import com.greencity.jdbc.entity.eventsEntity.EventRequesterEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EventRequesterDAO extends BaseDAO {

    public EventRequesterDAO(String login, String password, String url) {
        super(login, password, url);
    }

    public List<EventRequesterEntity> selectAll() {
        Statement statement = ManagerDAO.getInstance(url, login, password).getStatement();
        List<List<String>> rows;
        try {
            ResultSet rs = statement.executeQuery(EventRequesterEntity.SELECT_ALL);
            rows = ManagerDAO.parseResultSet(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ManagerDAO.closeAllStatements();
        }

        List<EventRequesterEntity> result = new ArrayList<>();
        for (List<String> row : rows) {
            result.add(EventRequesterEntity.parseRow(row));
        }
        return result;
    }

    public List<EventRequesterEntity> selectByEventId(Long eventId) {
        Statement statement = ManagerDAO.getInstance(url, login, password).getStatement();
        List<List<String>> rows;
        try {
            ResultSet rs = statement.executeQuery(String.format(EventRequesterEntity.FIND_BY_EVENT_ID, eventId));
            rows = ManagerDAO.parseResultSet(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ManagerDAO.closeAllStatements();
        }

        List<EventRequesterEntity> result = new ArrayList<>();
        for (List<String> row : rows) {
            result.add(EventRequesterEntity.parseRow(row));
        }
        return result;
    }

    public List<EventRequesterEntity> selectByUserId(Long userId) {
        Statement statement = ManagerDAO.getInstance(url, login, password).getStatement();
        List<List<String>> rows;
        try {
            ResultSet rs = statement.executeQuery(String.format(EventRequesterEntity.FIND_BY_USER_ID, userId));
            rows = ManagerDAO.parseResultSet(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ManagerDAO.closeAllStatements();
        }

        List<EventRequesterEntity> result = new ArrayList<>();
        for (List<String> row : rows) {
            result.add(EventRequesterEntity.parseRow(row));
        }
        return result;
    }
}
