package ru.chn.dto.payment.response.resume;

import lombok.Getter;
import lombok.Setter;
import ru.chn.dto.other.resume.ResumePreviewDTO;

import java.util.List;

@Getter
@Setter
public class ResumePreviewsResponse {
    private List<ResumePreviewDTO> resumes;
}
