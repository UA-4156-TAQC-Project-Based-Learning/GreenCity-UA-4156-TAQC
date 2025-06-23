package com.greencity.api.models.ecoNewsComments;

import lombok.Data;
import java.util.List;

@Data
public class ResponseCommentById {
    private Long id;
    private ResponseEcoNewsCommentAuthor author;
    private String text;
    private String createdDate;
    private List<String> additionalImages;
}

