package com.greencity.jdbc.services;

public abstract class BaseServise {
    private final String login;
    private final String password;
    private final String url;

    public BaseServise(String login, String password, String url) {
        this.login = login;
        this.password = password;
        this.url = url;
    }
}
