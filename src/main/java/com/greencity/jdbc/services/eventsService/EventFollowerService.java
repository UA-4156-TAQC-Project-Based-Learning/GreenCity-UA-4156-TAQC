package com.greencity.jdbc.services.eventsService;

import com.greencity.jdbc.dao.eventsDAO.EventFollowerDAO;
import com.greencity.jdbc.entity.eventsEntity.EventFollowerEntity;
import com.greencity.jdbc.services.BaseService;

import java.util.List;

public class EventFollowerService extends BaseService {
    private final EventFollowerDAO eventFollowerDAO;

    public EventFollowerService(String login, String password, String url) {
        super(login, password, url);
        this.eventFollowerDAO = new EventFollowerDAO(login, password, url);
    }

    public List<EventFollowerEntity> getAll() {
        return eventFollowerDAO.selectAll();
    }

    public List<EventFollowerEntity> getByEventId(Long eventId) {
        return eventFollowerDAO.selectByEventId(eventId);
    }

    public List<EventFollowerEntity> getByUserId(Long userId) {
        return eventFollowerDAO.selectByUserId(userId);
    }

}
