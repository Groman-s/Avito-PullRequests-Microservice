package com.goyanov.avitoprmanager.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TeamWithMembersDTO
{
    @JsonProperty("team_name")
    private String name;

    @JsonProperty("members")
    private List<UserFullDTO> members = new ArrayList<>();
}
