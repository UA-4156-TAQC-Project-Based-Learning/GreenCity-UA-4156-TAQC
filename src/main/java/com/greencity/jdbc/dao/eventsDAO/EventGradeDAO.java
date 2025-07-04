package com.greencity.jdbc.dao.eventsDAO;

import com.greencity.jdbc.dao.BaseDAO;
import com.greencity.jdbc.dao.ManagerDAO;
import com.greencity.jdbc.entity.eventsEntity.EventGradeEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EventGradeDAO extends BaseDAO {

    public EventGradeDAO(String login, String password, String url) {
        super(login, password, url);
    }

    public List<EventGradeEntity> selectAll() {
        Statement statement = ManagerDAO.getInstance(url, login, password).getStatement();
        List<List<String>> rows;
        try {
            ResultSet rs = statement.executeQuery(EventGradeEntity.SELECT_ALL);
            rows = ManagerDAO.parseResultSet(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ManagerDAO.closeAllStatements();
        }

        List<EventGradeEntity> grades = new ArrayList<>();
        for (List<String> row : rows) {
            grades.add(EventGradeEntity.parseRow(row));
        }
        return grades;
    }

    public EventGradeEntity selectById(Long id) {
        Statement statement = ManagerDAO.getInstance(url, login, password).getStatement();
        List<List<String>> rows;
        try {
            ResultSet rs = statement.executeQuery(String.format(EventGradeEntity.FIND_BY_ID, id));
            rows = ManagerDAO.parseResultSet(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ManagerDAO.closeAllStatements();
        }
        if (rows.isEmpty()) {
            return null;
        }
        return EventGradeEntity.parseRow(rows.get(0));
    }

    public List<EventGradeEntity> selectByEventId(Long eventId) {
        Statement statement = ManagerDAO.getInstance(url, login, password).getStatement();
        List<List<String>> rows;
        try {
            ResultSet rs = statement.executeQuery(String.format(EventGradeEntity.FIND_BY_EVENT_ID, eventId));
            rows = ManagerDAO.parseResultSet(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ManagerDAO.closeAllStatements();
        }

        List<EventGradeEntity> grades = new ArrayList<>();
        for (List<String> row : rows) {
            grades.add(EventGradeEntity.parseRow(row));
        }
        return grades;
    }

    public List<EventGradeEntity> selectByUserId(Long userId) {
        Statement statement = ManagerDAO.getInstance(url, login, password).getStatement();
        List<List<String>> rows;
        try {
            ResultSet rs = statement.executeQuery(String.format(EventGradeEntity.FIND_BY_USER_ID, userId));
            rows = ManagerDAO.parseResultSet(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ManagerDAO.closeAllStatements();
        }

        List<EventGradeEntity> grades = new ArrayList<>();
        for (List<String> row : rows) {
            grades.add(EventGradeEntity.parseRow(row));
        }
        return grades;
    }
}
