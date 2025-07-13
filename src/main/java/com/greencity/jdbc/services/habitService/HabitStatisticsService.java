package com.greencity.jdbc.services.habitService;

import com.greencity.jdbc.dao.habitDAO.HabitStatisticsDAO;
import com.greencity.jdbc.entity.HabitEntity;
import com.greencity.jdbc.entity.habitEntity.HabitStatisticsEntity;
import com.greencity.jdbc.services.BaseService;

import java.util.List;

public class HabitStatisticsService extends BaseService {
    private HabitStatisticsDAO habitStatisticsDAO;

    public HabitStatisticsService(String login, String password, String url) {
        super(login, password, url);
    }

    public List<HabitStatisticsEntity> getAll() {
        return habitStatisticsDAO.selectAll();
    }

    public Integer getAmountOfItemsByHabitAssignId(Long habitAssignId){
        return habitStatisticsDAO.amountOfItemsByHabitAssignId(habitAssignId);
    }
}
