package ru.chn.dto.other.project;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ProjectPreviewDTO {
    private Long id;
    private String title;
    private String description;
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    @JsonProperty("folowers_count")
    private Long folowersCount;
    @JsonProperty("patch_count")
    private Long patchCount;


}
