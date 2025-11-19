package com.goyanov.avitoprmanager.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.goyanov.avitoprmanager.model.PullRequest;
import com.goyanov.avitoprmanager.model.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PullRequestFullDTO
{
    @JsonProperty("pull_request_id")
    private String id;

    @JsonProperty("pull_request_name")
    private String name;

    @JsonProperty("author_id")
    private String authorId;

    @JsonProperty("status")
    private PullRequest.Status status;

    @JsonProperty("assigned_reviewers")
    private List<String> reviewers = new ArrayList<>();
}
