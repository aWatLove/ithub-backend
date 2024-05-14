package ru.chn.dto.response;

import lombok.Getter;
import lombok.Setter;
import ru.chn.dto.UserPreviewDTO;

import java.util.List;

@Getter
@Setter
public class ProjectLikesResponse {
    private List<UserPreviewDTO> likes;
}
