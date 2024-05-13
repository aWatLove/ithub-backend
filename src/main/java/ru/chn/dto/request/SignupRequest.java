package ru.chn.dto.request;

import lombok.Data;

@Data
public class SignupRequest {
    private String username;
    private String firstname;
    private String lastname;
    private String password;
}
