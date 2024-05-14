package ru.chn.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonProperty("user_id")
    private Long userId;
    @JsonProperty("project_id")
    private Long projectId;
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    private String text;

    public Comment(Long userId, Long projectId, LocalDateTime createdAt, String text) {
        this.userId = userId;
        this.projectId = projectId;
        this.createdAt = createdAt;
        this.text = text;
    }
}
