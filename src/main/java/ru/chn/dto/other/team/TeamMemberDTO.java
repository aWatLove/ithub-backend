package ru.chn.dto.other.team;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TeamMemberDTO {
    private Long id;
    private String username;
    private String avatar;
    private String role;


}
