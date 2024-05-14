package ru.chn.dto.payment.request.claim;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClaimPostRequest {
    @JsonProperty("team_id")
    private Long teamId;
    @JsonProperty("resume_id")
    private Long resumeId;
}
