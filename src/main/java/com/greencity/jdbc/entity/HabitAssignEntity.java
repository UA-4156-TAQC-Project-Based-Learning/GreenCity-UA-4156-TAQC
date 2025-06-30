package com.greencity.jdbc.entity;

import lombok.Data;
import java.sql.Timestamp;
import java.util.List;

@Data
public class HabitAssignEntity {

    public static final String SELECT_ALL = """
                                                   SELECT
                                                    id,
                                                    create_date,
                                                    user_id,
                                                    habit_id,
                                                    duration,
                                                    habit_streak,
                                                    working_days,
                                                    last_enrollment,
                                                    status,
                                                    progress_notification_has_displayed,
                                                    is_private
                                                    FROM habit_assign;
            """;

    public static final String SELECT_ALL_BY_USER_ID = """
                                                   SELECT
                                                        id,
                                                        create_date,
                                                        user_id,
                                                        habit_id,
                                                        duration,
                                                        habit_streak,
                                                        working_days,
                                                        last_enrollment,
                                                        status,
                                                        progress_notification_has_displayed,
                                                        is_private
                                                    FROM habit_assign
                                                    WHERE user_id = '%s';
            """;

    private Integer id;
    private Timestamp create_date;
    private Integer user_id;
    private Integer habit_id;
    private Integer duration;
    private Integer habit_streak;
    private Integer working_days;
    private Timestamp last_enrollment;
    private String status;
    private Boolean progress_notification_has_displayed;
    private Boolean is_private;

    public HabitAssignEntity(Integer id, Timestamp create_date, Integer user_id, Integer habit_id, Integer duration,
                             Integer habit_streak, Integer working_days, Timestamp last_enrollment, String status,
                             Boolean progress_notification_has_displayed, Boolean is_private) {
        this.id = id;
        this.create_date = create_date;
        this.user_id = user_id;
        this.habit_id = habit_id;
        this.duration = duration;
        this.habit_streak = habit_streak;
        this.working_days = working_days;
        this.last_enrollment = last_enrollment;
        this.status = status;
        this.progress_notification_has_displayed = progress_notification_has_displayed;
        this.is_private = is_private;
    }

    public static HabitAssignEntity parseRow(List<String> row) {
        return new HabitAssignEntity(Integer.parseInt(row.get(0)),
                Timestamp.valueOf(row.get(1)),
                Integer.parseInt(row.get(2)),
                Integer.parseInt(row.get(3)),
                Integer.parseInt(row.get(4)),
                Integer.parseInt(row.get(5)),
                Integer.parseInt(row.get(6)),

                Timestamp.valueOf(row.get(6)),
                row.get(7),
                Boolean.parseBoolean(row.get(8)),
                Boolean.parseBoolean(row.get(9)));

    }
}

