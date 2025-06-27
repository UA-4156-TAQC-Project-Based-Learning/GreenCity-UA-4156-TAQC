package com.greencity.jdbc.entity;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class CommentEntity {
    public static final String SELECT_ALL = """
            SELECT
                id,
                parent_comment_id,
                user_id,
                article_type,
                article_id,
                text,
                created_date,
                modified_date,
                status
            FROM
                comments;""";
    public static final String SELECT_BY_USER_ID = """
            SELECT
                id,
                parent_comment_id,
                user_id,
                article_type,
                article_id,
                text,
                created_date,
                modified_date,
                status
            FROM
                WHERE user_id = '%s'""";

    private Integer id;
    private Integer parent_comment_id;
    private Integer user_id;
    private String article_type;
    private Integer article_id;
    private String text;
    private Timestamp created_date;
    private Timestamp modified_date;
    private String status;

    public CommentEntity(Integer id, Integer parent_comment_id, Integer user_id, String article_type, Integer article_id, String text, Timestamp created_date, Timestamp modified_date, String status) {
        this.id = id;
        this.parent_comment_id = parent_comment_id;
        this.user_id = user_id;
        this.article_type = article_type;
        this.article_id = article_id;
        this.text = text;
        this.created_date = created_date;
        this.modified_date = modified_date;
        this.status = status;
    }

    public static CommentEntity parseRow(List<String> row) {
        return new CommentEntity(Integer.parseInt(row.get(0)), Integer.parseInt(row.get(1)), Integer.parseInt(row.get(2)), row.get(3), Integer.parseInt(row.get(4)), row.get(5), Timestamp.valueOf(row.get(6)), Timestamp.valueOf(row.get(7)), row.get(8));
    }
}
