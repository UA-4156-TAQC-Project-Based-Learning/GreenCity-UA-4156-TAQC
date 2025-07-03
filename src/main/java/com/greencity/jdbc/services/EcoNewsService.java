package com.greencity.jdbc.services;

import com.greencity.jdbc.dao.EcoNewsDAO;
import com.greencity.jdbc.dao.UserDAO;
import com.greencity.jdbc.entity.EcoNewsEntity;

import java.util.List;

public class EcoNewsService extends BaseServise {

    private final EcoNewsDAO ecoNewsDAO;
    private final UserDAO userDAO;

    public EcoNewsService(String login, String password, String url) {
        super(login, password, url);
        ecoNewsDAO = new EcoNewsDAO(login,password,url);
        userDAO = new UserDAO(login, password, url);
    }

    public List<EcoNewsEntity> getAllNews() {
        return ecoNewsDAO.selectAllNews();
    }

    public List<EcoNewsEntity> getNewsByUserId(Integer userId) {
        return ecoNewsDAO.selectByAuthorId(userId);
    }

    public List<EcoNewsEntity> getNewByTitle(String title) {
        return ecoNewsDAO.selectByTitle(title);
    }

    public List<EcoNewsEntity> getById(Integer id) {
        return ecoNewsDAO.selectById(id);
    }

    public void deleteNews(Integer id) {
        ecoNewsDAO.deleteNewsById(id);
    }

}
