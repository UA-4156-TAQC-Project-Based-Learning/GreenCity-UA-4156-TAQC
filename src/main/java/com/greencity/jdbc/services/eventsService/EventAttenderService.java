package com.greencity.jdbc.services.eventsService;

import com.greencity.jdbc.dao.eventsDAO.EventAttenderDAO;
import com.greencity.jdbc.entity.eventsEntity.EventAttenderEntity;
import com.greencity.jdbc.services.BaseServise;

import java.util.List;

public class EventAttenderService extends BaseServise {
    private final EventAttenderDAO eventAttenderDAO;

    public EventAttenderService(String login, String password, String url) {
        super(login, password, url);
        this.eventAttenderDAO = new EventAttenderDAO(login, password, url);
    }

    public List<EventAttenderEntity> getAll() {
        return eventAttenderDAO.selectAll();
    }

    public List<EventAttenderEntity> getByEventId(Long eventId) {
        return eventAttenderDAO.selectByEventId(eventId);
    }

    public List<EventAttenderEntity> getByUserId(Long userId) {
        return eventAttenderDAO.selectByUserId(userId);
    }

}
