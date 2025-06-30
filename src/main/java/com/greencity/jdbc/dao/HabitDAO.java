package com.greencity.jdbc.dao;

import com.greencity.jdbc.entity.HabitEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HabitDAO extends BaseDAO{

    public HabitDAO(String login, String password, String url) {
        super(login, password, url);
    }

    public List<HabitEntity> selectAllHabits() {
        Statement statement = ManagerDAO.getInstance(url, login, password).getStatement();
        List<List<String>> rows = null;
        try {
            ResultSet resultSet = statement.executeQuery(HabitEntity.SELECT_ALL);
            rows = ManagerDAO.parseResultSet(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ManagerDAO.closeAllStatements();
        }
        List<HabitEntity> habits = new ArrayList<>();
        for (List<String> row : rows) {
            habits.add(HabitEntity.parseRow(row));
        }
        return habits;
    }

}
