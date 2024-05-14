package ru.chn.dto.payment.request.project;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import ru.chn.model.Tag;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ProjectPostRequest {
    @JsonProperty("team_id")
    private Long teamId;
    private String title;
    private String description;
    @JsonProperty("html_info")
    private String htmlInfo;
    private String stack;
    private List<Long> tags;
}
