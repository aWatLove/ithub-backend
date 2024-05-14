package ru.chn.dto.other.team;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeamPreviewDTO {
    private Long id;
    private String name;
    private String avatar;
    @JsonProperty("folowers_count")
    private Long folowersCount;
    @JsonProperty("is_folow")
    private boolean isFolow;

    public TeamPreviewDTO(Long id, String name, String avatar, Long folowersCount) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.folowersCount = folowersCount;
        this.isFolow = false;
    }
}
