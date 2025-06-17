package com.greencity.api.models.econews;

import com.greencity.api.models.user.ResponseAuthor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;

@Data
public class ResponseEcoNews {

    private Date creationDate;
    private Object imagePath;
    private int id;
    private String title;
    private String content;
    private String shortInfo;
    private ResponseAuthor author;
    private int likes;
    private int dislikes;
    private int countComments;
    private boolean hidden;
    private ArrayList<String> tagsEn;
    private ArrayList<String> tagsUk;

}
