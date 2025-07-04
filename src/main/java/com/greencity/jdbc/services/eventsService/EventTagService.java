package com.greencity.jdbc.services.eventsService;

import com.greencity.jdbc.dao.eventsDAO.EventTagDAO;
import com.greencity.jdbc.entity.eventsEntity.EventTagEntity;
import com.greencity.jdbc.services.BaseServise;

import java.util.List;

public class EventTagService extends BaseServise {
    private final EventTagDAO eventTagDAO;

    public EventTagService(String login, String password, String url) {
        super(login, password, url);
        this.eventTagDAO = new EventTagDAO(login, password, url);
    }

    public List<EventTagEntity> getAll() {
        return eventTagDAO.selectAll();
    }

    public List<EventTagEntity> getByEventId(Long eventId) {
        return eventTagDAO.selectByEventId(eventId);
    }

    public List<EventTagEntity> getByTagId(Long tagId) {
        return eventTagDAO.selectByTagId(tagId);
    }

}
