package com.greencity.api.models.ecoNewsComments;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
public class ResponseComment {
    private Long id;
    private String createdDate;
    private String modifiedDate;
    private ResponseEcoNewsCommentAuthor author;
    private Long parentCommentId;
    private String text;
    private Integer replies;
    private Integer likes;
    private Integer dislikes;
    private Boolean currentUserLiked;
    private Boolean currentUserDisliked;
    private String status;
    private List<String> additionalImages;
}
