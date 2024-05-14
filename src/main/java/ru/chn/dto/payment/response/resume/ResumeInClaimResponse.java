package ru.chn.dto.payment.response.resume;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResumeInClaimResponse {
    private Long id;
    private String title;

    public ResumeInClaimResponse(Long id, String title) {
        this.id = id;
        this.title = title;
    }
}
