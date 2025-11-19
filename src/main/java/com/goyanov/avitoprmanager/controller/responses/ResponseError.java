package com.goyanov.avitoprmanager.controller.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseError
{
    private String code;
    private String message;
}
