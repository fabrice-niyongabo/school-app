package com.fabrice.studentregistration.studentregistration.dto;

import lombok.Data;

@Data // auto getters and setters
public class RegisterStudentRequest {
    private String firstname;
    private String lastname;
    private String email;
}
