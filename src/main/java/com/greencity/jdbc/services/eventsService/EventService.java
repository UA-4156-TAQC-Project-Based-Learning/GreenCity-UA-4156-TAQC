package com.greencity.jdbc.services.eventsService;

import com.greencity.jdbc.dao.eventsDAO.EventDAO;
import com.greencity.jdbc.entity.eventsEntity.EventEntity;
import com.greencity.jdbc.services.BaseServise;

import java.util.List;

public class EventService extends BaseServise {
    private final EventDAO eventDAO;

    public EventService(String login, String password, String url) {
        super(login, password, url);
        this.eventDAO = new EventDAO(login, password, url);
    }

    public List<EventEntity> getAllEvents() {
        return eventDAO.selectAll();
    }

    public EventEntity getEventById(Long id) {
        return eventDAO.selectById(id);
    }
}
