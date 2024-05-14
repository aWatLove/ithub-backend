package ru.chn.dto.payment.response.team;

import lombok.Getter;
import lombok.Setter;
import ru.chn.dto.other.team.TeamPreviewDTO;

import java.util.List;

@Getter
@Setter
public class TeamPreviewResponse {
    private List<TeamPreviewDTO> teams;
}
