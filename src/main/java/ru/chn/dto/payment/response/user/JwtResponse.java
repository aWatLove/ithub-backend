package ru.chn.dto.payment.response.user;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

public class JwtResponse {
    private String token;
    private String type = "Bearer";

    private Long id;

    private String username;
    private String avatar;
    private String firstname;
    private String lastname;
    private String bioInfo;
    private String email;
    private String telegram;
    private String link;

    public JwtResponse(String token, Long id, String username, String avatar, String firstname, String lastname, String bioInfo, String email, String telegram, String link) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.avatar = avatar;
        this.firstname = firstname;
        this.lastname = lastname;
        this.bioInfo = bioInfo;
        this.email = email;
        this.telegram = telegram;
        this.link = link;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }


    public String getBioInfo() {
        return bioInfo;
    }

    public void setBioInfo(String bioInfo) {
        this.bioInfo = bioInfo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelegram() {
        return telegram;
    }

    public void setTelegram(String telegram) {
        this.telegram = telegram;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
