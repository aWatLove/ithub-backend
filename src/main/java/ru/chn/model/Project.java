package ru.chn.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
    @JsonProperty("owner_id")
    private Long ownerId;
    @JsonProperty("team_id")
    private Long teamId;
    private String title;
    private String description;
    @JsonProperty("html_info")
    private String htmlInfo;
    private String stack;
    @JsonProperty("likes_count")
    private Long likesCount;
    @JsonProperty("folowers_count")
    private Long folowersCount;
    @JsonProperty("patch_count")
    private Long patchCount;
}
