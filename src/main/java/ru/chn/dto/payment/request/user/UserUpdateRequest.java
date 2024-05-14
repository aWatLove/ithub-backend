package ru.chn.dto.payment.request.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class UserUpdateRequest {
    private String username;
    private String avatar;
    private String firstname;

    private String lastname;
    @JsonProperty("bio_info")
    private String bioInfo;
    private String email;
    private String telegram;
    private String link;
}
