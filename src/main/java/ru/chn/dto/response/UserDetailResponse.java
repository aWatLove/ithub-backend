package ru.chn.dto.response;

import lombok.Getter;
import lombok.Setter;
import ru.chn.dto.TeamPreviewDTO;

import java.util.List;

@Getter
@Setter
public class UserDetailResponse {
    private Long id;

    private String username;
    private String avatar;
    private String firstname;
    private String lastname;
    private String bioInfo;
    private String email;
    private String telegram;
    private String link;

    private List<TeamPreviewDTO> teams;
}
