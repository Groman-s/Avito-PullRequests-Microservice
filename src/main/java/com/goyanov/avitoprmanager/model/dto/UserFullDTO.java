package com.goyanov.avitoprmanager.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserFullDTO
{
    @JsonProperty("user_id")
    private String id;

    @JsonProperty("username")
    private String name;

    @JsonProperty("is_active")
    private boolean active;
}
