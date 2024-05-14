package ru.chn.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "patches")
public class Patch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    @JsonProperty("project_id")
    private Long projectId;
    private String title;
    private String description;
    @JsonProperty("likes_count")
    private Long likesCount;
}
