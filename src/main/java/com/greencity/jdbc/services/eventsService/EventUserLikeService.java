package com.greencity.jdbc.services.eventsService;

import com.greencity.jdbc.dao.eventsDAO.EventUserLikeDAO;
import com.greencity.jdbc.entity.eventsEntity.EventUserLikeEntity;
import com.greencity.jdbc.services.BaseServise;

import java.util.List;

public class EventUserLikeService extends BaseServise {
    private final EventUserLikeDAO eventUserLikeDAO;

    public EventUserLikeService(String login, String password, String url) {
        super(login, password, url);
        this.eventUserLikeDAO = new EventUserLikeDAO(login, password, url);
    }

    public List<EventUserLikeEntity> getAll() {
        return eventUserLikeDAO.selectAll();
    }

    public List<EventUserLikeEntity> getByEventId(Long eventId) {
        return eventUserLikeDAO.selectByEventId(eventId);
    }

    public List<EventUserLikeEntity> getByUserId(Long userId) {
        return eventUserLikeDAO.selectByUserId(userId);
    }

}
