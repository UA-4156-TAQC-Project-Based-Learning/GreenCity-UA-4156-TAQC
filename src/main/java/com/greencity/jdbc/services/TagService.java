package com.greencity.jdbc.services;

import com.greencity.jdbc.dao.TagDAO;
import com.greencity.jdbc.entity.TagEntity;
import com.greencity.jdbc.enums.TagType;

import java.util.List;

public class TagService extends BaseServise {
    private final TagDAO tagDAO;

    public TagService(String login, String password, String url) {
        super(login, password, url);
        tagDAO = new TagDAO(login, password, url);
    }

    public List<TagEntity> getAllTags() {
        return tagDAO.selectAll();
    }

    public List<TagEntity> getTagsByType(TagType type) {
        return tagDAO.selectByType(type.name());
    }


}
