package ru.chn.dto.payment.response.project;

import lombok.Getter;
import lombok.Setter;
import ru.chn.dto.other.project.ProjectPatchDTO;

import java.util.List;

@Getter
@Setter
public class ProjectPatchesResponse {
    private List<ProjectPatchDTO> patches;
}
