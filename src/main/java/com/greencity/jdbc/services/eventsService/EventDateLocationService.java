package com.greencity.jdbc.services.eventsService;

import com.greencity.jdbc.dao.eventsDAO.EventDateLocationDAO;
import com.greencity.jdbc.entity.eventsEntity.EventDateLocationEntity;
import com.greencity.jdbc.services.BaseServise;

import java.util.List;

public class EventDateLocationService extends BaseServise {
    private final EventDateLocationDAO eventDateLocationDAO;

    public EventDateLocationService(String login, String password, String url) {
        super(login, password, url);
        this.eventDateLocationDAO = new EventDateLocationDAO(login, password, url);
    }

    public List<EventDateLocationEntity> getAll() {
        return eventDateLocationDAO.selectAll();
    }

    public List<EventDateLocationEntity> getByEventId(Long eventId) {
        return eventDateLocationDAO.selectByEventId(eventId);
    }

}
