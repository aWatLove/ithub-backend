package ru.chn.dto.payment.response.tag;

import lombok.Getter;
import lombok.Setter;
import ru.chn.dto.other.tag.TagPreviewDTO;

import java.util.List;

@Getter
@Setter
public class TagPreviewsResponse {
    private List<TagPreviewDTO> tags;
}
