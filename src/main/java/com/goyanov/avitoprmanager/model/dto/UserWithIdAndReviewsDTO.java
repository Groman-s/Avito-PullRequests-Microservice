package com.goyanov.avitoprmanager.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserWithIdAndReviewsDTO
{
    @JsonProperty("user_id")
    private String id;

    @JsonProperty("pull_requests")
    private List<PullRequestWithIdNameStatusAndAuthorIdDTO> pullRequests = new ArrayList<>();
}
