package ru.chn.dto.other.project;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import ru.chn.model.Patch;

import java.time.LocalDateTime;

@Getter
@Setter
public class ProjectPatchDTO {
    private Long id;
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    @JsonProperty("project_id")
    private Long projectId;
    private String title;
    private String description;
    @JsonProperty("likes_count")
    private Long likesCount;
    @JsonProperty("is_liked")
    private boolean isLiked;

    public static ProjectPatchDTO build(Patch patch) {
        ProjectPatchDTO ppd = new ProjectPatchDTO();
        ppd.id = patch.getId();
        ppd.createdAt = patch.getCreatedAt();
        ppd.projectId = patch.getProjectId();
        ppd.title = patch.getTitle();
        ppd.description = patch.getDescription();
        ppd.likesCount = patch.getLikesCount();
        ppd.isLiked = false;
        return ppd;
    }
}
