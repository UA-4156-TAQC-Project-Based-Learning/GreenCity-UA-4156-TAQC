package com.greencity.jdbc.entity;

import lombok.Data;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.util.List;

@Data
public class EcoNewsEntity {

    public static final String SELECT_ALL = """
                                               SELECT
                                                    id,
                                                    creation_date,
                                                    image_path,
                                                    author_id,
                                                    text,
                                                    title,
                                                    source,
                                                    short_info,
                                                    hidden
                                               FROM
                                                    eco_news""";

    public static final String SELECT_BY_USERID = SELECT_ALL + " WHERE author_id = %s;";
    public static final String SELECT_BY_TITLE = SELECT_ALL + " WHERE title = '%s'";
    public static final String SELECT_BY_ID = SELECT_ALL + " WHERE id = %s";


    private Integer id;
    private Timestamp creation_date;
    private String image_path;
    private Integer author_id;
    private String text;
    private String title;
    private String source;
    private String short_info;
    private Boolean hidden;

    public EcoNewsEntity(Integer id,
                         Timestamp create_date,
                         String image_path,
                         Integer author_id,
                         String text,
                         String title,
                         String source,
                         String short_info,
                         Boolean hidden) {
        this.id = id;
        this.creation_date = create_date;
        this.image_path = image_path;
        this.author_id = author_id;
        this.text = text;
        this.title = title;
        this.source = source;
        this.short_info = short_info;
        this.hidden = hidden;
    }

    public static EcoNewsEntity parseRow(List<String> row) {
        Timestamp timestamp = Timestamp.from(OffsetDateTime.parse(row.get(1).replace(" ", "T")).toInstant());
        return new EcoNewsEntity(
                Integer.parseInt(row.get(0)),
                timestamp,
                row.get(2),
                Integer.parseInt(row.get(3)),
                row.get(4),
                row.get(5),
                row.get(6),
                row.get(7),
                Boolean.parseBoolean(row.get(8))
        );
    }
}
