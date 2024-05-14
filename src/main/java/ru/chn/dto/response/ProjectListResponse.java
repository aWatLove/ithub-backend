package ru.chn.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProjectListResponse {
    private List<ProjectDetailsResponse> projects;
}
