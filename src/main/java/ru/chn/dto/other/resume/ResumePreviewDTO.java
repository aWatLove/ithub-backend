package ru.chn.dto.other.resume;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResumePreviewDTO {
    private Long id;
    @JsonProperty("user_id")
    private Long userId;
    private String title;
    @JsonProperty("html_info")
    private String htmlInfo;
    private String email;
    private String Telegram;
    private String link;
}
