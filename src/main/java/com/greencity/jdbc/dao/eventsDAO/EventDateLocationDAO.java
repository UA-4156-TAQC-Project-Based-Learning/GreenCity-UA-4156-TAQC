package com.greencity.jdbc.dao.eventsDAO;

import com.greencity.jdbc.dao.BaseDAO;
import com.greencity.jdbc.dao.ManagerDAO;
import com.greencity.jdbc.entity.eventsEntity.EventDateLocationEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EventDateLocationDAO extends BaseDAO {

    public EventDateLocationDAO(String login, String password, String url) {
        super(login, password, url);
    }

    public List<EventDateLocationEntity> selectAll() {
        Statement statement = ManagerDAO.getInstance(url, login, password).getStatement();
        List<List<String>> rows;
        try {
            ResultSet rs = statement.executeQuery(EventDateLocationEntity.SELECT_ALL);
            rows = ManagerDAO.parseResultSet(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ManagerDAO.closeAllStatements();
        }

        List<EventDateLocationEntity> result = new ArrayList<>();
        for (List<String> row : rows) {
            result.add(EventDateLocationEntity.parseRow(row));
        }
        return result;
    }

    public List<EventDateLocationEntity> selectByEventId(Long eventId) {
        Statement statement = ManagerDAO.getInstance(url, login, password).getStatement();
        List<List<String>> rows;
        try {
            ResultSet rs = statement.executeQuery(String.format(EventDateLocationEntity.FIND_BY_EVENT_ID, eventId));
            rows = ManagerDAO.parseResultSet(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ManagerDAO.closeAllStatements();
        }

        List<EventDateLocationEntity> result = new ArrayList<>();
        for (List<String> row : rows) {
            result.add(EventDateLocationEntity.parseRow(row));
        }
        return result;
    }
}
