package ru.chn.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResumeInClaimResponse {
    private Long id;
    private String header;

    public ResumeInClaimResponse(Long id, String header) {
        this.id = id;
        this.header = header;
    }
}
