package ru.chn.dto.response;

import lombok.Getter;
import lombok.Setter;
import ru.chn.dto.ProjectPatchDTO;

import java.util.List;

@Getter
@Setter
public class ProjectPatchesResponse {
    private List<ProjectPatchDTO> patches;
}
