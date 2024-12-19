package com.fabrice.studentregistration.studentregistration.dto;

import lombok.Data;

@Data //getters and setters
public class ErrorResponse {
    //defining the format of our error response
    private String message;
}
