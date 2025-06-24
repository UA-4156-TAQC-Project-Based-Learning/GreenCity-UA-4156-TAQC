package com.greencity.api.models.econews;

import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
public class ResponseEcoNews {

    private Long id;
    private String title;
    private String content;
    private String shortInfo;
    private ResponseAuthor author;
    private Date creationDate;
    private String imagePath;
    private String source;
    private List<String> tagsEn;
    private List<String> tagsUk;
    private int likes;
    private int dislikes;
    private boolean hidden;
    private int countComments;
    private int countOfEcoNews;
    private Boolean favorite;

}
