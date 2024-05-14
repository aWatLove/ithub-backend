package ru.chn.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import ru.chn.dto.ProjectTeamPreviewDTO;
import ru.chn.model.Tag;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ProjectDetailsResponse {
    private Long id;
    private ProjectTeamPreviewDTO team;
    private String title;
    private String description;
    private String html_info;
    private String stack;
    @JsonProperty("likes_count")
    private Long likesCount;
    @JsonProperty("folowers_count")
    private Long folowersCount;
    @JsonProperty("patch_count")
    private Long patchCount;
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
    @JsonProperty("is_folow")
    private boolean isFolow;
    @JsonProperty("is_liked")
    private boolean isLiked;
    private List<Tag> tags;

    public ProjectDetailsResponse(Long id, String title, String description, String html_info, String stack, Long likesCount, Long folowersCount, Long patchCount, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.html_info = html_info;
        this.stack = stack;
        this.likesCount = likesCount;
        this.folowersCount = folowersCount;
        this.patchCount = patchCount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.tags = new ArrayList<>();
    }
}
