package ru.chn.dto.response;

import lombok.Getter;
import lombok.Setter;
import ru.chn.dto.UserPreviewDTO;

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
