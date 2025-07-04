package com.greencity.jdbc.services.eventsService;

import com.greencity.jdbc.dao.eventsDAO.EventGradeDAO;
import com.greencity.jdbc.entity.eventsEntity.EventGradeEntity;
import com.greencity.jdbc.services.BaseServise;

import java.util.List;

public class EventGradeService extends BaseServise {
    private final EventGradeDAO eventGradeDAO;

    public EventGradeService(String login, String password, String url) {
        super(login, password, url);
        this.eventGradeDAO = new EventGradeDAO(login, password, url);
    }

    public List<EventGradeEntity> getAll() {
        return eventGradeDAO.selectAll();
    }

    public EventGradeEntity getById(Long id) {
        return eventGradeDAO.selectById(id);
    }

    public List<EventGradeEntity> getByEventId(Long eventId) {
        return eventGradeDAO.selectByEventId(eventId);
    }

    public List<EventGradeEntity> getByUserId(Long userId) {
        return eventGradeDAO.selectByUserId(userId);
    }

}
