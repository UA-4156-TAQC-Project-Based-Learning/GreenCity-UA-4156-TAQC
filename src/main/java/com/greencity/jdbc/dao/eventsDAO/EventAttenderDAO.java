package com.greencity.jdbc.dao.eventsDAO;

import com.greencity.jdbc.dao.BaseDAO;
import com.greencity.jdbc.dao.ManagerDAO;
import com.greencity.jdbc.entity.eventsEntity.EventAttenderEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EventAttenderDAO extends BaseDAO {

    public EventAttenderDAO(String login, String password, String url) {
        super(login, password, url);
    }

    public List<EventAttenderEntity> selectAll() {
        Statement statement = ManagerDAO.getInstance(url, login, password).getStatement();
        List<List<String>> rows;
        try {
            ResultSet rs = statement.executeQuery(EventAttenderEntity.SELECT_ALL);
            rows = ManagerDAO.parseResultSet(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ManagerDAO.closeAllStatements();
        }

        List<EventAttenderEntity> attenders = new ArrayList<>();
        for (List<String> row : rows) {
            attenders.add(EventAttenderEntity.parseRow(row));
        }
        return attenders;
    }

    public List<EventAttenderEntity> selectByEventId(Long eventId) {
        Statement statement = ManagerDAO.getInstance(url, login, password).getStatement();
        List<List<String>> rows;
        try {
            ResultSet rs = statement.executeQuery(String.format(EventAttenderEntity.FIND_BY_EVENT_ID, eventId));
            rows = ManagerDAO.parseResultSet(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ManagerDAO.closeAllStatements();
        }

        List<EventAttenderEntity> attenders = new ArrayList<>();
        for (List<String> row : rows) {
            attenders.add(EventAttenderEntity.parseRow(row));
        }
        return attenders;
    }

    public List<EventAttenderEntity> selectByUserId(Long userId) {
        Statement statement = ManagerDAO.getInstance(url, login, password).getStatement();
        List<List<String>> rows;
        try {
            ResultSet rs = statement.executeQuery(String.format(EventAttenderEntity.FIND_BY_USER_ID, userId));
            rows = ManagerDAO.parseResultSet(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ManagerDAO.closeAllStatements();
        }

        List<EventAttenderEntity> attenders = new ArrayList<>();
        for (List<String> row : rows) {
            attenders.add(EventAttenderEntity.parseRow(row));
        }
        return attenders;
    }
}
