package com.greencity.api.models.ecoNewsComments;

import com.greencity.api.models.econews.ResponseAuthor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ResponseEcoNewsCommentAuthor extends ResponseAuthor {
    private String profilePicturePath;
}
