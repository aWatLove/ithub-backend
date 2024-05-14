package ru.chn.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProjectTeamPreviewDTO {
    private Long id;
    private String name;
    private String avatar;
}
