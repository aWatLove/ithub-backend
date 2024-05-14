package ru.chn.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "claims")
public class Claim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonProperty("team_id")
    private Long teamId;
    @JsonProperty("resume_id")
    private Long resumeId;
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
    private Boolean accepted;

    public Claim(Long teamId, Long resumeId, LocalDateTime createdAt, LocalDateTime updatedAt, Boolean accepted) {
        this.teamId = teamId;
        this.resumeId = resumeId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.accepted = accepted;
    }

    public Claim() {

    }
}
