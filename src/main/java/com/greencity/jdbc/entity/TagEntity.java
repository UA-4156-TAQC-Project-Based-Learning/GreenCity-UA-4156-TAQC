package com.greencity.jdbc.entity;

import lombok.Data;

import java.util.List;

@Data
public class TagEntity {
    public static final String SELECT_ALL = "SELECT * FROM tags";
    public static final String FIND_BY_TYPE = "SELECT id, type FROM tags WHERE type = '%s'";

    private Long id;
    private String type;

    public TagEntity(Long id, String type){
        this.id = id;
        this.type = type;
    }

    public static TagEntity parseRow(List<String>row) {
        Long id = Long.parseLong(row.get(0));
        String type = row.get(1);
        return new TagEntity(id, type);
    }

    @Override
    public String toString() {
        return "TagEntity{id=" + id + ", type='" + type + "'}";
    }
}