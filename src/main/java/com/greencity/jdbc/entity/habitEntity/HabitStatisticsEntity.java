package com.greencity.jdbc.entity.habitEntity;

import com.greencity.jdbc.entity.CommentEntity;

import java.sql.Timestamp;
import java.util.List;

public class HabitStatisticsEntity {
    public static final String SELECT_ALL = """
            SELECT
                id,
                rate,
                user_id,
                create_date,
                habit_assign_id,
                amount_of_items
            FROM
                habit_statistics;""";

    public static final String SELECT_AMOUNT_OF_ITEMS_BY_HABIT_ASSIGN_ID = """
            SELECT
                amount_of_items,
            FROM
                comments
            WHERE
                habit_assign_id = '%d'""";

    private Integer id;
    private String rate;
    private Timestamp create_date;
    private Long habit_assign_id;
    private Integer amount_of_items;

    public HabitStatisticsEntity(Integer id, String rate, Timestamp create_date, Long habit_assign_id, Integer amount_of_items) {
        this.id = id;
        this.rate = rate;
        this.create_date = create_date;
        this.habit_assign_id = habit_assign_id;
        this.amount_of_items = amount_of_items;
    }

    public static HabitStatisticsEntity parseRow(List<String> row) {
        return new HabitStatisticsEntity(Integer.parseInt(row.get(0)),
                row.get(1),
                Timestamp.valueOf(row.get(2)),
                Long.parseLong(row.get(3)),
                Integer.parseInt(row.get(4)));
    }
}
