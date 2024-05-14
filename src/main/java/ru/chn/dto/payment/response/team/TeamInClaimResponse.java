package ru.chn.dto.payment.response.team;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeamInClaimResponse {
    private Long id;
    private String name;
    private String avatar;

    public TeamInClaimResponse(Long id, String name, String avatar) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
    }
}
