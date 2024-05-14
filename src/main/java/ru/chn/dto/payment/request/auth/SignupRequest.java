package ru.chn.dto.payment.request.auth;

import lombok.Data;

@Data
public class SignupRequest {
    private String username;
    private String firstname;
    private String lastname;
    private String password;
}
