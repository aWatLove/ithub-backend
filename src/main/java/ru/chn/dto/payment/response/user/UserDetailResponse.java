package ru.chn.dto.payment.response.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import ru.chn.dto.other.user.UsersTeamPreviewDTO;

import java.util.List;

@Getter
@Setter
public class UserDetailResponse {
    private Long id;

    private String username;
    private String avatar;
    private String firstname;
    private String lastname;
    @JsonProperty("bio_info")
    private String bioInfo;
    private String email;
    private String telegram;
    private String link;

    private List<UsersTeamPreviewDTO> teams;

    public UserDetailResponse(Long id, String username, String avatar, String firstname, String lastname, String bioInfo, String email, String telegram, String link) {
        this.id = id;
        this.username = username;
        this.avatar = avatar;
        this.firstname = firstname;
        this.lastname = lastname;
        this.bioInfo = bioInfo;
        this.email = email;
        this.telegram = telegram;
        this.link = link;
    }
}
