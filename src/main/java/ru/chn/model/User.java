package ru.chn.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String avatar;
    private String firstname;
    private String lastname;
    private String password;
    private String bioInfo;
    private String email;
    private String telegram;
    private String link;
}
