package ru.chn.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "teams")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("owner_id")
    private Long ownerId;
    private String name;
    private String description;
    @JsonProperty("folowers_count")
    private Long folowersCount;
    private String avatar;
}
