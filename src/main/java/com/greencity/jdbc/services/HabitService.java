package com.greencity.jdbc.services;

import com.greencity.jdbc.dao.HabitDAO;
import com.greencity.jdbc.entity.HabitEntity;

import java.util.List;

public class HabitService extends BaseServise {
    private final HabitDAO habitDAO;

    public HabitService(String login, String password, String url, HabitDAO habitDAO) {
        super(login, password, url);
        this.habitDAO = habitDAO;
    }

    public List<HabitEntity> getAllHabits() {
        return habitDAO.selectAllHabits();
    }

}
