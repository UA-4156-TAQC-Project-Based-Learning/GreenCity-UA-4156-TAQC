package com.greencity.jdbc.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManagerDAO {
    private static volatile ManagerDAO instance = null;

    private final Map<Long, Connection> connections;
    private final String login;
    private final String password;
    private final String url;


    private ManagerDAO(String url, String login, String password) {
        this.connections = new HashMap<>();
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
        } catch (SQLException err) {
            throw new RuntimeException(err);
        }
        this.url = url;
        this.login = login;
        this.password = password;
    }

    public static ManagerDAO getInstance(String url, String login, String password) {
        if (instance == null) {
            synchronized (ManagerDAO.class) {
                if (instance == null) {
                    instance = new ManagerDAO(url, login, password);
                }
            }
        }
        return instance;
    }

    public static void closeAllStatements() {
        if (instance != null) {
            for (Map.Entry<Long, Connection> entry : instance.connections.entrySet()) {
                try {
                    entry.getValue().close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            instance.connections.clear();
        }
    }

    public static List<List<String>> parseResultSet(ResultSet resultSet) throws SQLException {
        List<List<String>> result = new ArrayList<>();
        int columCount = resultSet.getMetaData().getColumnCount();
        while (resultSet.next()) {
            List<String> row = new ArrayList<>();
            for (int i = 1; i <= columCount; i++) {
                row.add(resultSet.getString(i));
            }
            result.add(row);
        }
        return result;
    }

    private Connection createConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, login, password);
        } catch (SQLException err) {
            throw new RuntimeException(err);
        }
        return connection;
    }

    public Connection getConnection() {
        Long idThread = Thread.currentThread().threadId();
        Connection connection = connections.get(idThread);
        if (connection == null) {
            connection = createConnection();
            connections.put(idThread, connection);
        }
        return connection;
    }

    public void closeConnection() {
        Long idThread = Thread.currentThread().threadId();
        Connection connection = connections.get(idThread);
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            connections.remove(idThread);
        }
    }

    public Statement getStatement() {
        Statement statement = null;
        try {
            statement = getConnection().createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return statement;
    }

}
