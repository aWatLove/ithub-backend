package ru.chn.dto.payment.response.claim;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import ru.chn.dto.payment.response.team.TeamInClaimResponse;
import ru.chn.dto.payment.response.resume.ResumeInClaimResponse;

import java.time.LocalDateTime;

@Getter
@Setter
public class ClaimPostResponse {
    private Long id;
    private TeamInClaimResponse team;
    private ResumeInClaimResponse resume;
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
    private Boolean accepted;

}
