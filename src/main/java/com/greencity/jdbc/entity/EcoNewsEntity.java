package com.greencity.jdbc.entity;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class EcoNewsEntity {

    public static final String SELECT_ALL = """
                                               SELECT
                                                    id,
                                                    create_date,
                                                    image_path,
                                                    author_id,
                                                    title,
                                                    source,
                                                    short_info
                                               FROM
                                                    eco_news;""";

    private Integer id;
    private Timestamp create_date;
    private String image_path;
    private Integer author_id;
    private String title;
    private String source;
    private String short_info;

    public EcoNewsEntity(Integer id,
                         Timestamp create_date,
                         String image_path,
                         Integer author_id,
                         String title,
                         String source,
                         String short_info) {
        this.id = id;
        this.create_date = create_date;
        this.image_path = image_path;
        this.author_id = author_id;
        this.title = title;
        this.source = source;
        this.short_info = short_info;
    }

    public static EcoNewsEntity parseRow(List<String> row) {
        return new EcoNewsEntity(
                Integer.parseInt(row.get(0)),
                Timestamp.valueOf(row.get(1)),
                row.get(2),
                Integer.parseInt(row.get(3)),
                row.get(4),
                row.get(5),
                row.get(6)

        );
    }
}
