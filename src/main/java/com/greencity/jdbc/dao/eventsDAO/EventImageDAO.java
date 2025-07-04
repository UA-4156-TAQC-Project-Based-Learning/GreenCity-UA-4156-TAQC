package com.greencity.jdbc.dao.eventsDAO;

import com.greencity.jdbc.dao.BaseDAO;
import com.greencity.jdbc.dao.ManagerDAO;
import com.greencity.jdbc.entity.eventsEntity.EventImageEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EventImageDAO extends BaseDAO {

    public EventImageDAO(String login, String password, String url) {
        super(login, password, url);
    }

    public List<EventImageEntity> selectAll() {
        Statement statement = ManagerDAO.getInstance(url, login, password).getStatement();
        List<List<String>> rows;
        try {
            ResultSet rs = statement.executeQuery(EventImageEntity.SELECT_ALL);
            rows = ManagerDAO.parseResultSet(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ManagerDAO.closeAllStatements();
        }

        List<EventImageEntity> images = new ArrayList<>();
        for (List<String> row : rows) {
            images.add(EventImageEntity.parseRow(row));
        }
        return images;
    }

    public List<EventImageEntity> selectByEventId(Long eventId) {
        Statement statement = ManagerDAO.getInstance(url, login, password).getStatement();
        List<List<String>> rows;
        try {
            ResultSet rs = statement.executeQuery(String.format(EventImageEntity.FIND_BY_EVENT_ID, eventId));
            rows = ManagerDAO.parseResultSet(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ManagerDAO.closeAllStatements();
        }

        List<EventImageEntity> images = new ArrayList<>();
        for (List<String> row : rows) {
            images.add(EventImageEntity.parseRow(row));
        }
        return images;
    }
}
