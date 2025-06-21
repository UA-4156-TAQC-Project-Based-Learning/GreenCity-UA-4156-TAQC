package com.greencity.api.models.ecoNewsComments;

import lombok.Data;

import java.util.List;

@Data
public class ResponseAddComment {
    private Long id;
    private ResponseEcoNewsCommentAuthor author;
    private String text;
//    private Date createdDate;
    private String createdDate;
    private List<String> additionalImages;
}
