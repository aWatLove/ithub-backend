package ru.chn.dto.response;

import lombok.Getter;
import lombok.Setter;
import ru.chn.dto.TagPreviewDTO;

import java.util.List;

@Getter
@Setter
public class TagPreviewsResponse {
    private List<TagPreviewDTO> tags;
}
