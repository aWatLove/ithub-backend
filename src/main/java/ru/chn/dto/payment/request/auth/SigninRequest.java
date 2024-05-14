package ru.chn.dto.payment.request.auth;

import lombok.Data;

@Data
public class SigninRequest {
    private String username;
    private String password;
}
