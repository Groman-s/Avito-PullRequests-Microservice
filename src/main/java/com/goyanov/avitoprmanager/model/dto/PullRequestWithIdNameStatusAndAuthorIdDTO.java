package com.goyanov.avitoprmanager.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.goyanov.avitoprmanager.model.PullRequest;
import lombok.Data;

@Data
public class PullRequestWithIdNameStatusAndAuthorIdDTO
{
    @JsonProperty("pull_request_id")
    private String id;

    @JsonProperty("pull_request_name")
    private String name;

    @JsonProperty("author_id")
    private String authorId;

    @JsonProperty("status")
    private PullRequest.Status status;
}
