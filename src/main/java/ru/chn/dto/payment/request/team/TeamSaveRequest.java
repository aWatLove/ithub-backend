package ru.chn.dto.payment.request.team;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeamSaveRequest {
    private String name;
    private String description;
    private String avatar;
}
