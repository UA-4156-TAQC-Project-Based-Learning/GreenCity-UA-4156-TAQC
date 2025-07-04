package com.greencity.jdbc.services.eventsService;

import com.greencity.jdbc.dao.eventsDAO.EventRequesterDAO;
import com.greencity.jdbc.entity.eventsEntity.EventRequesterEntity;
import com.greencity.jdbc.services.BaseServise;

import java.util.List;

public class EventRequesterService extends BaseServise {
    private final EventRequesterDAO eventRequesterDAO;

    public EventRequesterService(String login, String password, String url) {
        super(login, password, url);
        this.eventRequesterDAO = new EventRequesterDAO(login, password, url);
    }

    public List<EventRequesterEntity> getAll() {
        return eventRequesterDAO.selectAll();
    }

    public List<EventRequesterEntity> getByEventId(Long eventId) {
        return eventRequesterDAO.selectByEventId(eventId);
    }

    public List<EventRequesterEntity> getByUserId(Long userId) {
        return eventRequesterDAO.selectByUserId(userId);
    }

}
