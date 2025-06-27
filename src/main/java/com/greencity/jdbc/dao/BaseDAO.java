package com.greencity.jdbc.dao;

public class BaseDAO {
    protected final String login;
    protected final String password;
    protected final String url;

    public BaseDAO(String login, String password, String url) {
        this.login = login;
        this.password = password;
        this.url = url;
    }
}
