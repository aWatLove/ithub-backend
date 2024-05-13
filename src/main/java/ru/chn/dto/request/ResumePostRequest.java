package ru.chn.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResumePostRequest {
    private String title;
    @JsonProperty("html_info")
    private String htmlInfo;
}
