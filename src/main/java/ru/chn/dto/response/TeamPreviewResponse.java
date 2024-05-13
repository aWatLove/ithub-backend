package ru.chn.dto.response;

import lombok.Getter;
import lombok.Setter;
import ru.chn.dto.TeamPreviewDTO;

import java.util.List;

@Getter
@Setter
public class TeamPreviewResponse {
    private List<TeamPreviewDTO> teams;
}
