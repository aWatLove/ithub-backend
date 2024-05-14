package ru.chn.dto.payment.response.team;

import lombok.Getter;
import lombok.Setter;
import ru.chn.dto.other.user.UserPreviewDTO;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TeamFolowersResponse {
    private List<UserPreviewDTO> folowers;

    public TeamFolowersResponse() {
        this.folowers = new ArrayList<>();
    }
}
