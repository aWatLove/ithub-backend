package ru.chn.dto.payment.request.resume;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResumePutRequest {
    private String title;
    @JsonProperty("html_info")
    private String htmlInfo;
    private String email;
    private String Telegram;
    private String link;
}
