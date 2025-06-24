package com.greencity.api.models.ecoNewsComments;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestAddComment {

    private String text;
    private Long parentCommentId;
}
