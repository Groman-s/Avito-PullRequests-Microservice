package com.goyanov.avitoprmanager.controller.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UnsuccessfulResponse
{
    private ResponseError error;

    public static UnsuccessfulResponse withError(String code, String message)
    {
        return new UnsuccessfulResponse(new ResponseError(code, message));
    }
}
