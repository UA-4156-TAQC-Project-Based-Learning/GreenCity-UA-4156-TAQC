package com.greencity.jdbc.services.eventsService;

import com.greencity.jdbc.dao.eventsDAO.EventImageDAO;
import com.greencity.jdbc.entity.eventsEntity.EventImageEntity;
import com.greencity.jdbc.services.BaseService;

import java.util.List;

public class EventImageService extends BaseService {
    private final EventImageDAO eventImageDAO;

    public EventImageService(String login, String password, String url) {
        super(login, password, url);
        this.eventImageDAO = new EventImageDAO(login, password, url);
    }

    public List<EventImageEntity> getAll() {
        return eventImageDAO.selectAll();
    }

    public List<EventImageEntity> getByEventId(Long eventId) {
        return eventImageDAO.selectByEventId(eventId);
    }

}
