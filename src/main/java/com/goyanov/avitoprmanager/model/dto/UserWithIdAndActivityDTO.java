package com.goyanov.avitoprmanager.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserWithIdAndActivityDTO
{
    @JsonProperty("user_id")
    private String id;

    @JsonProperty("is_active")
    private boolean active;
}
