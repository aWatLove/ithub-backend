package ru.chn.dto.response;

import lombok.Getter;
import lombok.Setter;
import ru.chn.dto.ResumePreviewDTO;

import java.util.List;

@Getter
@Setter
public class ClaimGetAllResponse {
    private List<ClaimPostResponse> claims;
}
