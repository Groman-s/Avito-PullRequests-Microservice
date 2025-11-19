package com.goyanov.avitoprmanager.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PullRequestWithIdNameAndAuthorIdDTO
{
    @JsonProperty("pull_request_id")
    private String id;

    @JsonProperty("pull_request_name")
    private String name;

    @JsonProperty("author_id")
    private String authorId;
}
