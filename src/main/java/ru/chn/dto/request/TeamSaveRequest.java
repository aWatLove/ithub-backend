package ru.chn.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeamSaveRequest {
    private String name;
    private String description;
    private String avatar;
}
