package com.greencity.api.models.ecoNewsComments;

import lombok.Data;

import java.util.List;

@Data
public class CommentsPage {
    private List<ResponseComment> page;
    private int totalElements;
    private int currentPage;
    private int totalPages;
}
