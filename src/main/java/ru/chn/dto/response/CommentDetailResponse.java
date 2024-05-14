package ru.chn.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.chn.dto.UserPreviewDTO;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class CommentDetailResponse {
    private Long id;
    private UserPreviewDTO user;
    private String text;
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
}
