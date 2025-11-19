package com.goyanov.avitoprmanager.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserFullWithTeamNameDTO
{
    @JsonProperty("user_id")
    private String id;

    @JsonProperty("username")
    private String name;

    @JsonProperty("team_name")
    private String teamName;

    @JsonProperty("is_active")
    private boolean active;
}
