package com.greencity.jdbc.dao.eventsDAO;

import com.greencity.jdbc.dao.BaseDAO;
import com.greencity.jdbc.dao.ManagerDAO;
import com.greencity.jdbc.entity.eventsEntity.EventTagEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EventTagDAO extends BaseDAO {

    public EventTagDAO(String login, String password, String url) {
        super(login, password, url);
    }

    public List<EventTagEntity> selectAll() {
        Statement statement = ManagerDAO.getInstance(url, login, password).getStatement();
        List<List<String>> rows;
        try {
            ResultSet rs = statement.executeQuery(EventTagEntity.SELECT_ALL);
            rows = ManagerDAO.parseResultSet(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ManagerDAO.closeAllStatements();
        }

        List<EventTagEntity> result = new ArrayList<>();
        for (List<String> row : rows) {
            result.add(EventTagEntity.parseRow(row));
        }
        return result;
    }

    public List<EventTagEntity> selectByEventId(Long eventId) {
        Statement statement = ManagerDAO.getInstance(url, login, password).getStatement();
        List<List<String>> rows;
        try {
            ResultSet rs = statement.executeQuery(String.format(EventTagEntity.FIND_BY_EVENT_ID, eventId));
            rows = ManagerDAO.parseResultSet(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ManagerDAO.closeAllStatements();
        }

        List<EventTagEntity> result = new ArrayList<>();
        for (List<String> row : rows) {
            result.add(EventTagEntity.parseRow(row));
        }
        return result;
    }

    public List<EventTagEntity> selectByTagId(Long tagId) {
        Statement statement = ManagerDAO.getInstance(url, login, password).getStatement();
        List<List<String>> rows;
        try {
            ResultSet rs = statement.executeQuery(String.format(EventTagEntity.FIND_BY_TAG_ID, tagId));
            rows = ManagerDAO.parseResultSet(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ManagerDAO.closeAllStatements();
        }

        List<EventTagEntity> result = new ArrayList<>();
        for (List<String> row : rows) {
            result.add(EventTagEntity.parseRow(row));
        }
        return result;
    }
}
