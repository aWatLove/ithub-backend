package ru.chn.dto;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TeamPreviewDTO {
    private Long id;
    private String name;
    private String avatar;
    private String role;
}
