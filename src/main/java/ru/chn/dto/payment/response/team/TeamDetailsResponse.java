package ru.chn.dto.payment.response.team;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import ru.chn.dto.other.project.ProjectPreviewDTO;
import ru.chn.dto.other.team.TeamMemberDTO;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TeamDetailsResponse {
    private Long id;
    private String name;
    private String description;
    @JsonProperty("folowers_count")
    private Long folowersCount;
    private String avatar;
    @JsonProperty("owner_id")
    private Long ownerId;
    @JsonProperty("is_folow")
    private boolean isFolow;

    private List<TeamMemberDTO> members;
    private List<ProjectPreviewDTO> projects;

    public TeamDetailsResponse(Long id, String name, String description, Long folowersCount, String avatar, Long ownerId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.folowersCount = folowersCount;
        this.avatar = avatar;
        this.ownerId = ownerId;
        this.isFolow = false;
        this.members = new ArrayList<>();
        this.projects = new ArrayList<>();
    }
}
