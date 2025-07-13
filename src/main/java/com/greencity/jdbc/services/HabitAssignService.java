package com.greencity.jdbc.services;

import com.greencity.jdbc.dao.HabitAssignDAO;
import com.greencity.jdbc.entity.HabitAssignEntity;

import java.util.List;

public class HabitAssignService extends BaseService {

    private HabitAssignDAO habitAssignDAO;

    public HabitAssignService(String login, String password, String url, HabitAssignDAO habitAssignDAO) {
        super(login, password, url);
        this.habitAssignDAO = habitAssignDAO;
    }

    public List<HabitAssignEntity> getAllComments() {
        return habitAssignDAO.selectAllAssignedHabits();
    }

    public List<HabitAssignEntity> getCommentByUserEmail(Integer user_id) {
        return habitAssignDAO.selectAllAssignedHabitsByUserId(user_id);
    }

}
