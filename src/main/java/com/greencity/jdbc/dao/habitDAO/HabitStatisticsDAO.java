package com.greencity.jdbc.dao.habitDAO;

import com.greencity.jdbc.dao.BaseDAO;
import com.greencity.jdbc.dao.ManagerDAO;
import com.greencity.jdbc.entity.UserEntity;
import com.greencity.jdbc.entity.habitEntity.HabitStatisticsEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HabitStatisticsDAO extends BaseDAO {
    public HabitStatisticsDAO(String login, String password, String url) {
        super(login, password, url);
    }

    public List<HabitStatisticsEntity> selectAll() {
        Statement statement = ManagerDAO.getInstance(url, login, password).getStatement();
        List<List<String>> rows = null;
        try {
            ResultSet resultSet = statement.executeQuery(HabitStatisticsEntity.SELECT_ALL);
            rows = ManagerDAO.parseResultSet(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ManagerDAO.closeAllStatements();
        }
        List<HabitStatisticsEntity> habits = new ArrayList<>();
        for (List<String> row : rows) {
            habits.add(HabitStatisticsEntity.parseRow(row));
        }
        return habits;
    }

    public Integer amountOfItemsByHabitAssignId(Long habitAssignId) {
        Statement statement = ManagerDAO.getInstance(url, login, password).getStatement();
        ResultSet resultSet = null;
        Integer amountOfItems = 0;

        try {
            String query = String.format(HabitStatisticsEntity.SELECT_AMOUNT_OF_ITEMS_BY_HABIT_ASSIGN_ID, habitAssignId);
            resultSet = statement.executeQuery(query);
            List<List<String>> rows = ManagerDAO.parseResultSet(resultSet);

            if (!rows.isEmpty()) {
                amountOfItems = Integer.parseInt(rows.get(0).get(0));
            }else {
                return amountOfItems;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ManagerDAO.closeAllStatements();
        }

        return amountOfItems;
    }

}
