package ru.chn.dto.payment.response.project;

import lombok.Getter;
import lombok.Setter;
import ru.chn.dto.other.user.UserPreviewDTO;

import java.util.List;

@Getter
@Setter
public class ProjectFolowersResponse {
    private List<UserPreviewDTO> folowers;
}
