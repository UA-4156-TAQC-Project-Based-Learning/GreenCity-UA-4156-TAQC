package com.greencity.api.models.econews;

import com.google.common.primitives.Booleans;
import com.greencity.api.models.user.ResponseAuthor;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ResponseEcoNews {

    private Date creationDate;
    private String imagePath;
    private long id;
    private String title;
    private String content;
    private String shortInfo;
    private ResponseAuthor author;
    private int likes;
    private int dislikes;
    private int countComments;
    private Booleans hidden;
    private List<String> tagsEn;
    private List<String> tagsUk;

}
