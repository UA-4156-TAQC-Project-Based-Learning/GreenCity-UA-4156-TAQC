package com.greencity.jdbc.entity;

import lombok.*;
import java.sql.Timestamp;
import java.util.List;


@Data
public class HabitEntity {

    public static final String SELECT_ALL = """
                                                   SELECT
                                                    id,
                                                    image,
                                                    default_duration,
                                                    complexity,
                                                    user_id,
                                                    is_custom_habit,
                                                    is_deleted,
                                                    created_at
                                                    FROM habits;
            """;

    private Integer id;
    private String image;
    private Integer default_duration;
    private Integer complexity;
    private Integer user_id;
    private boolean is_custom_habit;
    private boolean is_deleted;
    private Timestamp created_at;

    public HabitEntity(Integer id,
                       String image,
                       Integer default_duration,
                       Integer complexity,
                       Integer user_id,
                       boolean is_custom_habit,
                       boolean is_deleted,
                       Timestamp created_at) {
        this.id = id;
        this.image = image;
        this.default_duration = default_duration;
        this.complexity = complexity;
        this.user_id = user_id;
        this.is_custom_habit = is_custom_habit;
        this.is_deleted = is_deleted;
        this.created_at = created_at;
    }

    public static HabitEntity parseRow(List<String> row) {
        return new HabitEntity(Integer.parseInt(row.get(0)),
                row.get(1),
                Integer.parseInt(row.get(2)),
                Integer.parseInt(row.get(3)),
                Integer.parseInt(row.get(4)),
                Boolean.parseBoolean(row.get(5)),
                Boolean.parseBoolean(row.get(6)),
                Timestamp.valueOf(row.get(7)));

    }
}
