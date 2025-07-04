package com.greencity.jdbc.services.eventsService;

import com.greencity.jdbc.dao.eventsDAO.EventUserDislikeDAO;
import com.greencity.jdbc.entity.eventsEntity.EventUserDislikeEntity;
import com.greencity.jdbc.services.BaseServise;

import java.util.List;

public class EventUserDislikeService extends BaseServise {
    private final EventUserDislikeDAO eventUserDislikeDAO;

    public EventUserDislikeService(String login, String password, String url) {
        super(login, password, url);
        this.eventUserDislikeDAO = new EventUserDislikeDAO(login, password, url);
    }

    public List<EventUserDislikeEntity> getAll() {
        return eventUserDislikeDAO.selectAll();
    }

    public List<EventUserDislikeEntity> getByEventId(Long eventId) {
        return eventUserDislikeDAO.selectByEventId(eventId);
    }

    public List<EventUserDislikeEntity> getByUserId(Long userId) {
        return eventUserDislikeDAO.selectByUserId(userId);
    }

}
