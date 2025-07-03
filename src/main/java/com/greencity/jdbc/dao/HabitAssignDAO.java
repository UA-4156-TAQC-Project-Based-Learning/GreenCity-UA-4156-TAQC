package com.greencity.jdbc.dao;

import com.greencity.jdbc.entity.HabitAssignEntity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HabitAssignDAO extends BaseDAO{

    public HabitAssignDAO(String login, String password, String url) {
        super(login, password, url);
    }

    public List<HabitAssignEntity> selectAllAssignedHabits() {
        Statement statement = ManagerDAO.getInstance(url, login, password).getStatement();
        List<List<String>> rows = null;
        try {
            ResultSet resultSet = statement.executeQuery(HabitAssignEntity.SELECT_ALL);
            rows = ManagerDAO.parseResultSet(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ManagerDAO.closeAllStatements();
        }
        List<HabitAssignEntity> habitsAssigned = new ArrayList<>();
        for (List<String> row : rows) {
            habitsAssigned.add(HabitAssignEntity.parseRow(row));
        }
        return habitsAssigned;
    }

    public List<HabitAssignEntity> selectAllAssignedHabitsByUserId(Integer user_id) {
        Statement statement = ManagerDAO.getInstance(url, login, password).getStatement();
        List<List<String>> rows = null;
        try {
            ResultSet resultSet = statement.executeQuery(String.format(HabitAssignEntity.SELECT_ALL_BY_USER_ID, user_id));
            rows = ManagerDAO.parseResultSet(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ManagerDAO.closeAllStatements();
        }
        List<HabitAssignEntity> habitsAssigenedToUser = new ArrayList<>();
        for (List<String> row : rows) {
            habitsAssigenedToUser.add(HabitAssignEntity.parseRow(row));
        }
        return habitsAssigenedToUser;
    }
}
